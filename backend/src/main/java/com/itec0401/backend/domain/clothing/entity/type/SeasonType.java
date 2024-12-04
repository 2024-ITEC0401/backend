package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum SeasonType {
    SPRING("봄"), SUMMER("여름"), AUTUMN("가을"), WINTER("겨울"), INVALID("예외");

    private final String title;

    SeasonType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static SeasonType convertString(String korSeasonType) {
        for (SeasonType season : SeasonType.values()) {
            if (Objects.equals(season.getTitle(), korSeasonType)) {
                return season;
            }
        }
        return INVALID;
    }
}
