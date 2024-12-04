package com.itec0401.backend.domain.clothing.entity.type;

import java.util.Objects;

public enum PatternType {
    SOLID("무지"), CHECK("체크"), STRIPE("스트라이프"), PRINT("프린트"), DOT("도트"),
    ANIMAL("애니멀"), FLORAL("플로럴"), TROPICAL("트로피칼"), PAISLEY("페이즐리"), ARGYLE("아가일"),
    MILITARY("밀리터리"), COLOR_BLOCK("컬러블럭"), REPEAT("반복"), OTHER("기타"), INVALID("예외");

    private final String title;

    PatternType(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public static PatternType convertString(String korPattern) {
        for (PatternType patternType : PatternType.values()) {
            if (Objects.equals(patternType.getTitle(), korPattern)) {
                return patternType;
            }
        }
        return INVALID;
    }
}
