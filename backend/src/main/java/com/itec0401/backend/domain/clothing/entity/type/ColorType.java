package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum ColorType {
    WHITE, CREAM, BEIGE, LIGHT_GRAY, DARK_GRAY,
    BLACK, LIGHT_PINK, YELLOW, LIGHT_GREEN, MINT,
    SKY_BLUE, LIGHT_PURPLE, PINK, CORAL, ORANGE,
    GREEN, BLUE, PURPLE, RED, CAMEL,
    BROWN, KHAKI, NAVY, WINE, GOLD,
    SILVER, INVALID;

    public static ColorType convertString(String color) {
        for (ColorType colorType : ColorType.values()) {
            if (Objects.equals(colorType.name().toLowerCase(), color.toLowerCase())) {
                return colorType;
            }
        }
        return INVALID;
    }
}
