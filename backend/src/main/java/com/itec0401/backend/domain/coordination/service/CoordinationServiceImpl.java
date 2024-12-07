package com.itec0401.backend.domain.coordination.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.clothing.service.ClothingService;
import com.itec0401.backend.domain.coordination.dto.*;
import com.itec0401.backend.domain.coordination.entity.Coordination;
import com.itec0401.backend.domain.coordination.repository.CoordinationRepository;
import com.itec0401.backend.domain.coordinationclothing.service.CoordinationClothingService;
import com.itec0401.backend.domain.user.entity.User;
import com.itec0401.backend.domain.user.service.UserService;
import com.itec0401.backend.global.exception.CoordinationNotFoundException;
import com.itec0401.backend.global.exception.DataManipulationException;
import com.itec0401.backend.global.exception.NullResponseFromApiException;
import com.itec0401.backend.global.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordinationServiceImpl implements CoordinationService {
    private final CoordinationRepository coordinationRepository;
    private final UserService userService;
    private final ClothingService clothingService;
    private final CoordinationClothingService coordinationClothingService;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public ResponseEntity<Void> coordRecommendBasic(BasicCodiRequestToSpring dto, Authentication authentication){
        System.out.println();
        // 옷들로 여러개 코디 받기
        // 권한 확인
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        System.out.println(validUser.getCoordinationList().isEmpty());
        // dto 가공 - clothing Service 사용
        BasicCodiRequestToPython toPythonBasic = BasicCodiRequestToPython.builder().clothing(
                clothingService.addClothingInfo(dto.getClothing()))
                .build();

        // python api 사용
        BasicCodiResponse apiResults = getBasicCodiSet(toPythonBasic);
        if (apiResults.getCodis().isEmpty()){
            throw new NullResponseFromApiException("Python API returned null response");
        }

        // 받아온 내용을 기반으로 Coordination 엔티티, CoordinationClothing 엔티티 생성
        // Clothing 엔티티에도 CoordinationClothing 연결을 해야할 듯

        for (CodiResponse temp : apiResults.getCodis()){
            Coordination coordination = Coordination.builder()
                    .name(temp.getName())
                    .description(temp.getDescription())
                    .hashtags(temp.getHashtags())
                    .user(validUser)
                    .build();
            coordinationRepository.save(coordination);  // 먼저 Coordination 생성하고 다른 테이블에서 참조해야 잘 참조됨.
            // CoordinationClothingService 사용하기 - N:M 매핑 하기
            for (Long id : temp.getClothing_ids()) {
                coordinationClothingService.createCoordinationClothing(coordination, clothingService.getClothingEntity(id, authentication));
            }
        }
        // 반환 값 없어도 될 듯
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private BasicCodiResponse getBasicCodiSet(BasicCodiRequestToPython dto){
        // Python 과 통신 - List<Dto> 형태의 결과를 반환
        BasicCodiResponse response;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://34.64.155.160:5000/get_codis";
        try{
            String jsonResponse = restTemplate.postForObject(url, dto, String.class);
            response = objectMapper.readValue(jsonResponse, BasicCodiResponse.class);
        } catch (ResourceAccessException e){
            System.out.println(e.getMessage());
            throw new NullResponseFromApiException("Error while using Python API");
        } catch (JsonProcessingException e){
            throw new DataManipulationException("Error while manipulating String to Json");
        }
        return response;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> coordRecommendUsingNaturalLanguage(NLCodiRequestToSpring dto, Authentication authentication){
        // 자연어 문장과 여러 옷들로 코디 1개 추천 받기
        // 권한 확인
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        // 유저가 가진 옷 정보를 List<ClothingData> Dto로 변환
        List<Long> clothingDataIds = new ArrayList<>();
        for (Clothing clothing : validUser.getClothingList()){
            clothingDataIds.add(clothing.getId());
        }

        // dto 가공
        NLCodiRequestToPython toPythonNL = NLCodiRequestToPython.builder()
                .natural_language(dto.getNatural_language())
                .clothing(clothingService.addClothingInfo(clothingDataIds))
                .build();

        // python api 사용
        CodiResponse apiResult = getCodiUsingNL(toPythonNL);
        System.out.println(apiResult.getClothing_ids().size());
        System.out.println(apiResult.getClothing_ids());
        // 받아온 내용을 기반으로 Coordination 엔티티, CoordinationClothing 엔티티 생성
        Coordination coordination = Coordination.builder()
                .name(apiResult.getName())
                .description(apiResult.getDescription())
                .hashtags(apiResult.getHashtags())
                .user(validUser)
                .build();
        coordinationRepository.save(coordination);
        // CoordinationClothingService 사용하기 - N:M 매핑 하기
        for (Long id : apiResult.getClothing_ids()) {
            if (id == null) continue;
            coordinationClothingService.createCoordinationClothing(coordination, clothingService.getClothingEntity(id, authentication));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private CodiResponse getCodiUsingNL(NLCodiRequestToPython dto){
        HttpEntity<NLCodiRequestToPython> requestEntity = new HttpEntity<>(dto);
        CodiResponse response;
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://34.64.155.160:5000/get_nl_codi";
        try{
            String jsonResponse = restTemplate.postForObject(url, dto, String.class);
            response = objectMapper.readValue(jsonResponse, CodiResponse.class);
        }
        catch (ResourceAccessException e){
            throw new NullResponseFromApiException("Error while using Python API");
        } catch (JsonProcessingException e){
            throw new DataManipulationException("Error while manipulating String to Json");
        }
        return response;
    }

    @Override
    public ResponseEntity<List<AllCodiInfo>> getAllCodiInfos(Authentication authentication){
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        List<AllCodiInfo> allCodiInfoList = new ArrayList<>();
        for (Coordination coordination : validUser.getCoordinationList()){
            allCodiInfoList.add(AllCodiInfo.builder()
                            .id(coordination.getId())
                            .name(coordination.getName())
                            .description(coordination.getDescription())
                            .hashtags(coordination.getHashtags())
                            .createdAt(coordination.getCreatedAt())
                            .clothingImages(clothingService.getClothingImages(validUser.getId(), coordination.getId()))
                            .build());
        }
        return new ResponseEntity<>(allCodiInfoList, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Integer> deleteCodiById(Long id, Authentication authentication){
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        Optional<Coordination> c = coordinationRepository.findByCoordinationId(id);
        if (c.isEmpty()){
            throw new CoordinationNotFoundException("Coordination not found");
        }
        Coordination coordination = c.get();
        coordinationClothingService.deleteCoordinationClothing(coordination.getCoordinationClothingList());
        return new ResponseEntity<>(coordinationRepository.deleteByTwoId(validUser.getId(), id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CodiDetails> getCoordinationDetails(Long id, Authentication authentication){
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();
        Optional<Coordination> c = coordinationRepository.findByCoordinationId(id);
        if (c.isEmpty()){
            throw new CoordinationNotFoundException("Coordination not found");
        }
        Coordination coordination = c.get();
        if (!coordination.getUser().equals(validUser)){
            throw new CoordinationNotFoundException("User does not belong to this coordination");
        }

        return new ResponseEntity<>(CodiDetails.builder()
                .id(coordination.getId())
                .name(coordination.getName())
                .description(coordination.getDescription())
                .hashtags(coordination.getHashtags())
                .createdAt(coordination.getCreatedAt())
                .clothingList(clothingService.getClothingDetails(validUser.getId(), id))
                .build(), HttpStatus.OK);
    }
}
