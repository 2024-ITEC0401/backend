package com.itec0401.backend.domain.clothing.entity.type;

public enum PatternType {
    SOLID, CHECK, STRIPE, PRINT, DOT,
    ANIMAL, FLORAL, TROPICAL, PAISLEY, ARGYLE,
    MILITARY, COLOR_BLOCK, REPEAT, OTHER, INVALID;

    public static PatternType convertString(String pattern) {
        for (PatternType patternType : PatternType.values()) {
            if (patternType.name().equalsIgnoreCase(pattern)) {
                return patternType;
            }
        }
        return INVALID;
    }
}
