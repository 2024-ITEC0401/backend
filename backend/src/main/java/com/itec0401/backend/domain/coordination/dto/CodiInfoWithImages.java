package com.itec0401.backend.domain.coordination.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CodiInfoWithImages {
    private final Long id;
    private final String name;
    private final String description;
    private final String hashtags;
    private final LocalDateTime createdAt;
    private final List<String> clothingImages;

    @Builder
    public CodiInfoWithImages(Long id, String name, String description, String hashtags, LocalDateTime createdAt, List<String> clothingImages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hashtags = hashtags;
        this.createdAt = createdAt;
        this.clothingImages = clothingImages;
    }
}
