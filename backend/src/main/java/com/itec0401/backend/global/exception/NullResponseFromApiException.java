package com.itec0401.backend.global.exception;

public class NullResponseFromApiException extends RuntimeException {
    public NullResponseFromApiException(String message) { super(message); }
}
