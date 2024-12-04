package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum ColorType {
    WHITE("흰색"), CREAM("크림"), BEIGE("베이지"), LIGHT_GRAY("연회색"), DARK_GRAY("진회색"),
    BLACK("검정"), LIGHT_PINK("연분홍"), YELLOW("노랑"), LIGHT_GREEN("연두"), MINT("민트"),
    SKY_BLUE("하늘색"), LIGHT_PURPLE("연보라"), PINK("분홍"), CORAL("코랄"), ORANGE("주황"),
    GREEN("초록"), BLUE("파랑"), PURPLE("보라"), RED("빨강"), CAMEL("카멜"),
    BROWN("갈색"), KHAKI("카키"), NAVY("네이비"), WINE("와인"), GOLD("골드"),
    SILVER("실버"), INVALID("예외");

    private final String title;

    ColorType(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public static ColorType convertString(String korColor) {
        for (ColorType colorType : ColorType.values()) {
            if (Objects.equals(colorType.getTitle(), korColor)) {
                return colorType;
            }
        }
        return INVALID;
    }
}
