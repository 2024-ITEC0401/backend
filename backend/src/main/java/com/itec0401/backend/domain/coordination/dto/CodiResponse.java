package com.itec0401.backend.domain.coordination.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CodiResponse {
    private final List<Long> clothing_ids;
    private final String description;
    private final List<String> hashtags;
    private final String name;

    @Builder
    @JsonCreator
    public CodiResponse(
            @JsonProperty("clothing_ids") List<Long> clothing_ids,
            @JsonProperty("description") String description,
            @JsonProperty("hashtags") List<String> hashtags,
            @JsonProperty("name") String name) {
        this.clothing_ids = clothing_ids;
        this.description = description;
        this.hashtags = hashtags;
        this.name = name;
    }
}
