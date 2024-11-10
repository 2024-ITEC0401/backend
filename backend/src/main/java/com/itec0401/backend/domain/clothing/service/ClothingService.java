package com.itec0401.backend.domain.clothing.service;


import com.itec0401.backend.domain.clothing.dto.ClothInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface ClothingService {
    ResponseEntity<ClothInfoDto> createClothing(ClothInfoDto clothInfoDto, Authentication authentication);
}
