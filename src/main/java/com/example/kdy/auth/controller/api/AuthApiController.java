package com.example.kdy.auth.controller.api;

import com.example.kdy.auth.dto.LoginRequest;
import com.example.kdy.auth.dto.TokenResponse;
import com.example.kdy.auth.dto.SignupRequest;
import com.example.kdy.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignupRequest signUpRequest) { // 회원 가입
        authService.signUp(signUpRequest);
        return ResponseEntity.ok(new TokenResponse("The join process is success", null));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) { // 회원 가입
        String jwtToken = authService.login(loginRequest);
        return ResponseEntity.ok(new TokenResponse("The login process is success", jwtToken));
    }
}
