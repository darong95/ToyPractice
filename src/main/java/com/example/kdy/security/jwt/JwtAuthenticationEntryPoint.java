package com.example.kdy.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException)
            throws IOException, ServletException {

        // Ajax 요청인지 브라우저 요청인지 구분
        String checkAccept = httpServletRequest.getHeader("Accept");

        // Ajax로 요청했을 경우
        if (checkAccept != null && checkAccept.contains("application/json")) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "[401] 인증이 필요합니다.");
            
        // 브라우저 요청일 경우 ➡️ 뷰가 Thymeleaf, SPA 구조에서는 불필요
        } else {
            httpServletResponse.sendRedirect("/auth/loginForm"); // 로그인 화면으로
        }
    }
}
