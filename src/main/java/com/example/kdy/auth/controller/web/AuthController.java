package com.example.kdy.auth.controller.web;

import com.example.kdy.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "auth/auth-loginForm";
    }

    @GetMapping("/signUpForm")
    public String signUpForm() {
        return "auth/auth-signUpForm";
    }
}
