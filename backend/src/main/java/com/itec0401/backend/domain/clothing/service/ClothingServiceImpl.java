package com.itec0401.backend.domain.clothing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itec0401.backend.domain.clothing.dto.ClothInfoDto;
import com.itec0401.backend.domain.clothing.dto.ClothRequestDto;
import com.itec0401.backend.domain.clothing.dto.DeleteImageUri;
import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.clothing.repository.ClothingRepository;
import com.itec0401.backend.domain.coordination.dto.ClothingData;
import com.itec0401.backend.domain.user.entity.User;
import com.itec0401.backend.domain.user.service.UserService;
import com.itec0401.backend.global.exception.ClothingNotFoundException;
import com.itec0401.backend.global.exception.NullResponseFromApiException;
import com.itec0401.backend.global.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingServiceImpl implements ClothingService {
    private final ClothingRepository clothingRepository;
    private final UserService userService;
    private final RestTemplate restTemplate;

    @Transactional
    public ResponseEntity<Void> createClothing(ClothRequestDto dto, Authentication authentication) {
        // 권한 확인
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        User validUser = user.get();
        Clothing c = Clothing.builder()
                .imageUri(dto.getImageUri())
                .name(dto.getName())
                .mainCategory(dto.getMainCategory())
                .subCategory(dto.getSubCategory())
                .baseColor(dto.getBaseColor())
                .pointColor(dto.getPointColor())
                .textile(dto.getTextile())
                .pattern(dto.getPattern())
                .season(dto.getSeason())
                .style(dto.getStyle())
                .description(dto.getDescription())
                .user(validUser)
                .build();

        clothingRepository.save(c);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClothInfoDto> getClothingById(Long id, Authentication authentication) {
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        Optional<Clothing> clothing = clothingRepository.findByClothingId(id);
        if (clothing.isEmpty()) {
            throw new ClothingNotFoundException("Clothing not found");
        }
        Clothing c = clothing.get();
        if (!c.getUser().equals(validUser)){
            throw new ClothingNotFoundException("Those clothes are not yours");
        }
        return new ResponseEntity<>(ClothInfoDto.toDto(c), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> updateClothing(Long id, ClothRequestDto dto, Authentication authentication) {
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        Optional<Clothing> clothing = clothingRepository.findByClothingId(id);
        if (clothing.isEmpty()) {
            throw new ClothingNotFoundException("Clothing not found");
        }
        Clothing c = clothing.get();
        if (!c.getUser().equals(validUser)){
            throw new ClothingNotFoundException("Those clothes are not yours");
        }
        c.update(dto);
        clothingRepository.save(c);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClothInfoDto>> getAllClothings(Authentication authentication) {
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        List<ClothInfoDto> clothInfoDtos = new ArrayList<>();
        for (Clothing c : validUser.getClothingList()){
            clothInfoDtos.add(ClothInfoDto.toDto(c));
        }
        return new ResponseEntity<>(clothInfoDtos, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Integer> deleteClothingById(Long id, Authentication authentication) {
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();

        Optional<Clothing> clothing = clothingRepository.findByClothingId(id);
        if (clothing.isEmpty()) {
            throw new ClothingNotFoundException("Clothing not found");
        }
        Clothing c = clothing.get();
        DeleteImageUri deleteImageUri = DeleteImageUri.builder().imageUri(c.getImageUri()).build();

        String url = "http://34.64.155.160:5000/delete_image";
        try{
            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // HttpEntity 객체 생성 (헤더와 바디 포함)
            HttpEntity<Object> requestEntity = new HttpEntity<>(deleteImageUri, headers);

            // DELETE 요청 보내기
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
            System.out.println(response.getStatusCode());
        } catch (ResourceAccessException e){
            System.out.println(e.getMessage());
            throw new NullResponseFromApiException("Error while using Python API");
        }
        return new ResponseEntity<>(clothingRepository.deleteByTwoId(validUser.getId(), id), HttpStatus.OK);
    }

    @Override
    public List<ClothingData> addClothingInfo(List<Long> clothingIds){
        List<ClothingData> clothingDataDtos = new ArrayList<>();
        for (Long id : clothingIds){
            Optional<Clothing> clothing = clothingRepository.findById(id);
            if (clothing.isEmpty()) {
                throw new ClothingNotFoundException("Clothing not found");
            }
            Clothing c = clothing.get();
            clothingDataDtos.add(ClothingData.toDto(c));
        }
        return clothingDataDtos;
    }

    @Override
    public Clothing getClothingEntity(Long id, Authentication authentication) {
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User validUser = user.get();
        Optional<Clothing> clothing = clothingRepository.findByClothingId(id);
        if (clothing.isEmpty()) {
            throw new ClothingNotFoundException("Clothing not found");
        }
        return clothing.get();
    }

    @Override
    public List<ClothInfoDto> getClothingDetails(Long user_id, Long coordination_id){
        List<ClothInfoDto> clothInfoDtos = new ArrayList<>();
        for (Clothing clothing : clothingRepository.findCodiDetails(user_id, coordination_id)){
            clothInfoDtos.add(ClothInfoDto.toDto(clothing));
        }
        return clothInfoDtos;
    }

    @Override
    public List<String> getClothingImages(Long user_id, Long coordination_id){
        List<String> clothingImages = new ArrayList<>();
        for (Clothing clothing : clothingRepository.findCodiDetails(user_id, coordination_id)){
            clothingImages.add(clothing.getImageUri());
        }
        return clothingImages;
    }
}
