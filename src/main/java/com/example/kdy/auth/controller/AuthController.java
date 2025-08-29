package com.example.kdy.auth.controller;

import com.example.kdy.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login-form")
    public String loginForm() {
        return "auth/login-form";
    }
}
