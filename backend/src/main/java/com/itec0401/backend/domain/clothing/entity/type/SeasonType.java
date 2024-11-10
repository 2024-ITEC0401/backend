package com.itec0401.backend.domain.clothing.entity.type;

public enum SeasonType {
    SPRING, SUMMER, AUTUMN, WINTER, INVALID;

    public static SeasonType convertString(String seasonType) {
        for (SeasonType season : SeasonType.values()) {
            if (season.name().equalsIgnoreCase(seasonType)) {
                return season;
            }
        }
        return INVALID;
    }
}
