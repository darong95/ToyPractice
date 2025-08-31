package com.example.kdy.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ApiResponse<T> {
    private final boolean success; // 성공 여부
    private final String message; // 응답 메시지
    private final T data; // 응답 데이터

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.of(true, message, data);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return ApiResponse.of(false, message, null);
    }

    public static <T> ApiResponse<T> fail(String message, T data) {
        return ApiResponse.of(false, message, data);
    }
}

