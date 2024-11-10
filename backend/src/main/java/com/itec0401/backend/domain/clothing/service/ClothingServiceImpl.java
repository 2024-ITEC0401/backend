package com.itec0401.backend.domain.clothing.service;

import com.itec0401.backend.domain.clothing.dto.ClothInfoDto;
import com.itec0401.backend.domain.clothing.entity.Clothing;
import com.itec0401.backend.domain.clothing.entity.type.Category;
import com.itec0401.backend.domain.clothing.entity.type.ColorType;
import com.itec0401.backend.domain.clothing.repository.ClothingRepository;
import com.itec0401.backend.domain.user.entity.User;
import com.itec0401.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingServiceImpl implements ClothingService {
    private final ClothingRepository clothingRepository;
    private final UserService userService;

    public ResponseEntity<ClothInfoDto> createClothing(ClothInfoDto clothInfoDto, Authentication authentication) {
        // 권한 확인
        Optional<User> user = userService.checkPermission(authentication);
        if (user.isEmpty())
            return new ResponseEntity<>(ClothInfoDto.builder().build(), HttpStatus.BAD_REQUEST);
        User validUser = user.get();
        Clothing c = Clothing.builder()
                .imageUri(clothInfoDto.getImageUri())
                .name(clothInfoDto.getName())
                .mainCategory(clothInfoDto.getMainCategory())
                .subCategory(clothInfoDto.getSubCategory())
                .baseColor(clothInfoDto.getBaseColor())
                .pointColor(clothInfoDto.getPointColor())
                .textile(clothInfoDto.getTextile())
                .pattern(clothInfoDto.getPattern())
                .season(clothInfoDto.getSeason())
                .style(clothInfoDto.getStyle())
                .description(clothInfoDto.getDescription())
                .user(validUser)
                .build();

        clothingRepository.save(c);

        return new ResponseEntity<>(ClothInfoDto.toDto(c), HttpStatus.CREATED);
    }
}
