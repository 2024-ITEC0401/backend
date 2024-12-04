package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum TextileType {
    COTTON("면"), LINEN("린넨"), POLYESTER("폴리에스테르"), KNIT_WOOL("니트/울"), FUR("퍼"),
    TWEED("트위드"), NYLON("나일론"), DENIM("데님"), LEATHER("가죽"), SUEDE("스웨이드"),
    VELVET("벨벳"), CHIFFON("쉬폰"), SILK("실크"), CORDUROY("코듀로이"), METALLIC("메탈릭"),
    LACE("레이스"), OTHER("기타"), INVALID("예외");

    private final String title;

    TextileType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static TextileType convertString(String korTextile){
        for (TextileType textileType : TextileType.values()) {
            if (Objects.equals(textileType.getTitle(), korTextile)) {
                return textileType;
            }
        }
        return INVALID;
    }
}
