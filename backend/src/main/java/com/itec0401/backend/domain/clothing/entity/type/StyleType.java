package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum StyleType {
    DAILY, WORK, DATE, CEREMONY, TRAVEL,
    HOMEWEAR, PARTY, EXERCISE, SPECIAL_DAY, SCHOOL,
    OTHER, INVALID;

    public static StyleType convertString(String styleType) {
        for (StyleType style : StyleType.values()) {
            if (Objects.equals(style.name().toLowerCase(), styleType.toLowerCase())) {
                return style;
            }
        }
        return StyleType.INVALID;
    }
}
