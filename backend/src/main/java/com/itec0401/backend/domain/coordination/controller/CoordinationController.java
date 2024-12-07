package com.itec0401.backend.domain.coordination.controller;

import com.itec0401.backend.domain.coordination.dto.BasicCodiRequestToSpring;
import com.itec0401.backend.domain.coordination.dto.CodiDetails;
import com.itec0401.backend.domain.coordination.dto.CodiInfoWithImages;
import com.itec0401.backend.domain.coordination.dto.NLCodiRequestToSpring;
import com.itec0401.backend.domain.coordination.service.CoordinationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coordination")
public class CoordinationController {
    private final CoordinationService coordinationService;

    @Operation(
            summary = "Basic 코디 추천 API",
            description = "옷 여러개 선택 후 요청하면, 그 옷을 이용해 입을 수 있는 코디를 1개 ~ 10개 추천해줌. " +
                    "추천된 결과가 바로 DB에 저장됨. " +
                    "내용을 열람하기 위해서는 특정 코디 상세 정보 열람 API 등을 사용해여함."
    )
    @PostMapping("/rec/basic")
    public ResponseEntity<Void> coordRecommendBasic(@RequestBody BasicCodiRequestToSpring dto, Authentication authentication){
        return coordinationService.coordRecommendBasic(dto, authentication);
    }

    @Operation(
            summary = "자연어를 이용한 코디 추천 API",
            description = "옷 여러개 선택 + 자연어 한 문장 을 이용해 요청하면, 그 자연어 상황에 맞는 코디를 1개 추천해줌. " +
                    "추천된 결과는 바로 DB에 저장됨."
    )
    @PostMapping("/rec/natural-language")
    public ResponseEntity<CodiInfoWithImages> coordRecommendUsingNaturalLanguage(@RequestBody NLCodiRequestToSpring dto, Authentication authentication){
        return coordinationService.coordRecommendUsingNaturalLanguage(dto, authentication);
    }

    @Operation(
            summary = "모든 코디 정보 열람 API",
            description = "코디의 상세 정보가 열람되지는 않음. 추후 수정해야할 수도...(이미지 정보를 같이 반환하는 방향으로..)"
    )
    @GetMapping
    public ResponseEntity<List<CodiInfoWithImages>> getAllCoordinationInfos(Authentication authentication){
        return coordinationService.getAllCodiInfoWithImages(authentication);
    }

    @Operation(
            summary = "특정 코디 삭제 API",
            description = "코디 id로 코디 삭제, 그 코디와 연관된 N:M 중간테이블에 있는 행도 당연히 다 삭제됨."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteCoordinationInfo(@PathVariable("id") Long id, Authentication authentication){
        return coordinationService.deleteCodiById(id, authentication);
    }

    @Operation(
            summary = "특정 코디 상세 정보 열람 API",
            description = "코디의 정보와 코디에 사용되는 옷의 상세 정보를 싹 다 담았음."
    )
    @GetMapping("/{id}")
    public ResponseEntity<CodiDetails> getCoordinationDetails(@PathVariable("id") Long id, Authentication authentication){
        return coordinationService.getCoordinationDetails(id, authentication);
    }

}
