package com.itec0401.backend.domain.coordination.dto;

import com.itec0401.backend.domain.clothing.dto.ClothInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CodiDetails {
    private final Long id;
    private final String name;
    private final String description;
    private final String hashtags;
    private final List<ClothInfoDto> clothingList;

    @Builder
    public CodiDetails(Long id, String name, String description, String hashtags, List<ClothInfoDto> clothingList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hashtags = hashtags;
        this.clothingList = clothingList;
    }
}
