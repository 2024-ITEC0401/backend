package com.itec0401.backend.domain.clothing.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteImageUri {
    private final String imageUri;

    @Builder
    public DeleteImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
