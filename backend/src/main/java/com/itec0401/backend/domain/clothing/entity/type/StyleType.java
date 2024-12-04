package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum StyleType {
    DAILY("데일리"), WORK("직장"), DATE("데이트"), CEREMONY("경조사"), TRAVEL("여행"),
    HOMEWEAR("홈웨어"), PARTY("파티"), EXERCISE("운동"), SPECIAL_DAY("특별한날"), SCHOOL("학교"),
    OTHER("기타"), INVALID("예외");

    private final String title;

    StyleType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static StyleType convertString(String korStyleType) {
        for (StyleType style : StyleType.values()) {
            if (Objects.equals(style.getTitle(), korStyleType)) {
                return style;
            }
        }
        return StyleType.INVALID;
    }
}
