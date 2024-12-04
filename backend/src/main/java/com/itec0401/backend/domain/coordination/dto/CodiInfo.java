package com.itec0401.backend.domain.coordination.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CodiInfo {
    private final Long id;
    private final String name;
    private final String description;
    private final String hashtags;

    @Builder
    public CodiInfo(Long id, String name, String description, String hashtags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hashtags = hashtags;
    }
}
