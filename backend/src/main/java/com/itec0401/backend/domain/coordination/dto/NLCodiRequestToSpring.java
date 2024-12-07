package com.itec0401.backend.domain.coordination.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class NLCodiRequestToSpring {
    private final String natural_language;

    @JsonCreator
    @Builder
    public NLCodiRequestToSpring(@JsonProperty("natural_language") String natural_language) {
        this.natural_language = natural_language;
    }
}
