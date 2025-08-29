package com.example.kdy.common.advice;

import com.example.kdy.auth.controller.api.AuthApiController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = {
        "com.example.kdy.auth.controller.api",
        "com.example.kdy.board.controller.api"
})
public class GlobalExceptionApiAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        log.info("[API ERROR] IllegalArgumentException 발생!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalArgumentException.getMessage());
    }
}