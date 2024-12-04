package com.itec0401.backend.domain.coordination.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class NLCodiRequestToPython {
    private final List<ClothingData> clothing;
    private final String natural_language;

    @Builder
    public NLCodiRequestToPython(List<ClothingData> clothing, String natural_language) {
        this.clothing = clothing;
        this.natural_language = natural_language;
    }
}
