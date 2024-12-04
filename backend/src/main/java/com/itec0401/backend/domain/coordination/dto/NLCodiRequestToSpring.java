package com.itec0401.backend.domain.coordination.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class NLCodiRequestToSpring {
    private final List<Long> clothing;
    private final String natural_language;

    @Builder
    public NLCodiRequestToSpring(List<Long> clothing, String natural_language) {
        this.clothing = clothing;
        this.natural_language = natural_language;
    }
}
