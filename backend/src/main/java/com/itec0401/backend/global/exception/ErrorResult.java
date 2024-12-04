package com.itec0401.backend.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResult {
    private final String code;
    private final String message;
}
