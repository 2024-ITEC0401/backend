package com.itec0401.backend.domain.clothing.controller;


import com.itec0401.backend.domain.clothing.dto.ClothInfoDto;
import com.itec0401.backend.domain.clothing.service.ClothingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mycloth")
public class ClothingController {
    private final ClothingService clothingService;

    @Operation(
            summary = "옷 등록",
            description = "Authentication으로 유저 판별 유저의 옷 등록",
            responses = {
                    @ApiResponse(responseCode = "200", description = "옷 등록 성공"),
                    @ApiResponse(responseCode = "401", description = "옷 등록 실패, 토큰 만료 여부 판단 바람")
            }
    )
    @PostMapping("/upload")
    public ResponseEntity<ClothInfoDto> createClothing(@RequestBody ClothInfoDto clothInfoDto, Authentication authentication){
        return clothingService.createClothing(clothInfoDto, authentication);
    }

    /*
    @PostMapping("/upload")
    public ResponseEntity<ClothInfoDto> uploadClothingImage(Authentication authentication, @RequestParam("file") MultipartFile file){
        return clothingService;
    }
    */

}
