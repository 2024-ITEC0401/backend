package com.itec0401.backend.domain.coordination.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class BasicCodiResponse {
    private final List<CodiResponse> codis;

    @Builder
    @JsonCreator
    public BasicCodiResponse(@JsonProperty("codis") List<CodiResponse> codis) {
        this.codis = codis;
    }
}
