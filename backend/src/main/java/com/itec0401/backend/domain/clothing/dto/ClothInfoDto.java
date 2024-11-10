package com.itec0401.backend.domain.clothing.dto;

import com.itec0401.backend.domain.clothing.entity.Clothing;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClothInfoDto {
    /* 용도 : 이미지를 Python 서버로 보냈을 때, 파이썬 서버가 보내준 정보를 담는 DTO */
    private String imageUri;
    private String name;
    private String mainCategory;
    private String subCategory;
    private String baseColor;
    private String pointColor;
    private String textile;
    private String pattern;
    private String season;
    private String style;
    private String description;

    public static ClothInfoDto toDto(Clothing clothing){
        return ClothInfoDto.builder()
                .imageUri(clothing.getImageUri())
                .name(clothing.getName())
                .mainCategory(clothing.getSubCategory().getParentCategoryName())
                .subCategory(clothing.getSubCategory().name())
                .baseColor(clothing.getBaseColor().name())
                .pointColor(clothing.getPointColor().name())
                .textile(clothing.getTextile().name())
                .pattern(clothing.getPattern().name())
                .season(clothing.getSeason().name())
                .style(clothing.getStyle().name())
                .description(clothing.getDescription())
                .build();
    }
}
