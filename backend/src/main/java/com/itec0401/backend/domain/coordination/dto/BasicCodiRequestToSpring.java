package com.itec0401.backend.domain.coordination.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BasicCodiRequestToSpring {
    private final List<Long> clothing;

    @JsonCreator
    @Builder
    public BasicCodiRequestToSpring(@JsonProperty("clothing") List<Long> clothing) {
        this.clothing = clothing;
    }
}
