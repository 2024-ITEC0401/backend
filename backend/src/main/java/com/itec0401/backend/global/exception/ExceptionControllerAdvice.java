package com.itec0401.backend.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResult> UserNotFoundException(UserNotFoundException e) {
        log.info("UserNotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResult.builder().code("400").message("UserNotFound-EX").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClothingNotFoundException.class)
    public ResponseEntity<ErrorResult> ClothingNotFoundException(ClothingNotFoundException e) {
        log.info("ClothingNotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResult.builder().code("400").message("ClothingNotFound-EX").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullResponseFromApiException.class)
    public ResponseEntity<ErrorResult> NullResponseFromApiException(NullResponseFromApiException e) {
        log.info("NullResponseFromApiException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResult.builder().code("400").message("NullResponseFromApi-EX").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataManipulationException.class)
    public ResponseEntity<ErrorResult> DataManipulationException(DataManipulationException e) {
        log.info("DataManipulationException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResult.builder().code("400").message("DataManipulation-EX").build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoordinationNotFoundException.class)
    public ResponseEntity<ErrorResult> CoordinationNotFoundException(CoordinationNotFoundException e) {
        log.info("CoordinationNotFoundException: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResult.builder().code("400").message("CoordinationNotFound-EX").build(), HttpStatus.BAD_REQUEST);
    }
}
