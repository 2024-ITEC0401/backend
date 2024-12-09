package com.itec0401.backend.domain.clothing.service;


import com.itec0401.backend.domain.clothing.dto.ClothInfoDto;
import com.itec0401.backend.domain.clothing.dto.ClothRequestDto;
import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.coordination.dto.ClothingData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClothingService {
    ResponseEntity<Void> createClothing(ClothRequestDto dto, Authentication authentication);
    ResponseEntity<ClothInfoDto> getClothingById(Long id, Authentication authentication);
    ResponseEntity<Void> updateClothing(Long id, ClothRequestDto dto, Authentication authentication);
    ResponseEntity<List<ClothInfoDto>> getAllClothings(Authentication authentication);
    ResponseEntity<Integer> deleteClothingById(Long id, Authentication authentication);
    List<ClothingData> addClothingInfo(List<Long> clothingIds);
    Clothing getClothingEntity(Long id, Authentication authentication);
    List<ClothInfoDto> getClothingDetails(Long user_id, Long coordination_id);
    List<String> getClothingImages(Long user_id, Long coordination_id);
}
