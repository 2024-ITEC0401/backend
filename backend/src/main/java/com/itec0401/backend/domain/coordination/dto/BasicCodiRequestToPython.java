package com.itec0401.backend.domain.coordination.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BasicCodiRequestToPython {
    private final List<ClothingData> clothing;

    @Builder
    public BasicCodiRequestToPython(List<ClothingData> clothing) {
        this.clothing = clothing;
    }
}
