package com.example.kdy.common.advice;

import com.example.kdy.common.dto.ApiResponse;
import com.example.kdy.common.exception.CustomValidException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = {
        "com.example.kdy.auth.controller.api",
        "com.example.kdy.board.controller.api"
})
public class GlobalExceptionApiAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.badRequest().body(ApiResponse.fail(illegalArgumentException.getMessage()));
    }

    // 유효성 검사 (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errorMap = new LinkedHashMap<>();

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(ApiResponse.fail("필수 항목을 확인해 주세요.", errorMap));
    }

    // 유효성 검사 (수동)
    @ExceptionHandler(CustomValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleCustomValidationException(CustomValidException ex) {
        // 실패 응답을 담아 400 Bad Request 반환
        return ResponseEntity.badRequest().body(ApiResponse.fail(ex.getMessage(), ex.getErrorMap()));
    }
}