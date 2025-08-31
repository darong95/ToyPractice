package com.example.kdy.auth.controller.api;

import com.example.kdy.auth.dto.LoginRequest;
import com.example.kdy.auth.dto.TokenResponse;
import com.example.kdy.auth.dto.SignupRequest;
import com.example.kdy.auth.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @Value("${jwt.cookie-name}")
    private String jwtCookieName;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid@RequestBody SignupRequest signUpRequest) { // 회원 가입
        authService.signUp(signUpRequest);
        return ResponseEntity.ok("The register process is success");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) { // 로그인
        // 토큰 발급
        String jwtToken = authService.login(loginRequest);

        // HttpOnly Cookie 저장 ➡️ 브라우저에 jwtCookieName 이름으로 Token을 저장하겠다는 말
        ResponseCookie responseCookie = ResponseCookie.from(jwtCookieName, jwtToken)
                .httpOnly(true) // XSS (JavaScript 접근 방지)
                .secure(false) // HTTPS 전용
                .sameSite("Strict") // CSRF 보호
                .path("/")
                .maxAge(60 * 60) // 1시간
                .build();

        // Cookie Header에 Set-Cookie ➡️ 브라우저에 쿠키 저장
        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return ResponseEntity.ok(new TokenResponse("The login process is success", jwtToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse httpServletResponse) {
        // JWT 쿠키 삭제 (maxAge=0)
        ResponseCookie responseCookie = ResponseCookie.from(jwtCookieName, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(0) // 즉시 만료
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());

        return ResponseEntity.ok("The logout process is success");
    }
}
