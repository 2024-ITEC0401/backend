package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum TextileType {
    COTTON, LINEN, POLYESTER, KNIT_WOOL, FUR,
    TWEED, NYLON, DENIM, LEATHER, SUEDE,
    VELVET, CHIFFON, SILK, CORDUROY, METALLIC,
    LACE, OTHER, INVALID;

    public static TextileType convertString(String textile){
        for (TextileType textileType : TextileType.values()) {
            if (Objects.equals(textileType.name().toLowerCase(), textile)) {
                return textileType;
            }
        }
        return INVALID;
    }
}
