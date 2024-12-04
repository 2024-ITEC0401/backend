package com.itec0401.backend.domain.coordination.dto;


import com.itec0401.backend.domain.clothing.entity.Clothing;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClothingData {
    private Long clothing_id;
    private final String name;
    private final String description;
    private final String baseColor;
    private final String pointColor;
    private final String mainCategory;
    private final String subCategory;
    private final String pattern;
    private final String season;
    private final String textile;
    private final String style;

    public static ClothingData toDto(Clothing c){
        return ClothingData.builder()
                .clothing_id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .baseColor(c.getBaseColor().getTitle())
                .pointColor(c.getPointColor().getTitle())
                .mainCategory(c.getMainCategory().getTitle())
                .subCategory(c.getSubCategory().getTitle())
                .pattern(c.getPattern().getTitle())
                .season(c.getSeason().getTitle())
                .textile(c.getTextile().getTitle())
                .style(c.getStyle().getTitle())
                .build();
    }
}
