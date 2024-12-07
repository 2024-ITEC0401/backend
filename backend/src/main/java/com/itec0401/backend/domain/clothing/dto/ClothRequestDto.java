package com.itec0401.backend.domain.clothing.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ClothRequestDto {
    // Create, Update 둘 다 이 형식
    private final String imageUri;
    private final String name;
    private final String mainCategory;
    private final String subCategory;
    private final String baseColor;
    private final String pointColor;
    private final String textile;
    private final String pattern;
    private final String season;
    private final String style;
    private final String description;

    @Builder
    public ClothRequestDto(
            String imageUri,
            String name,
            String mainCategory,
            String subCategory,
            String baseColor,
            String pointColor,
            String textile,
            String pattern,
            String season,
            String style,
            String description) {
        this.imageUri = imageUri;
        this.name = name;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.baseColor = baseColor;
        this.pointColor = pointColor;
        this.textile = textile;
        this.pattern = pattern;
        this.season = season;
        this.style = style;
        this.description = description;
    }
}
