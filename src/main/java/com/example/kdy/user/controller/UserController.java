package com.example.kdy.user.controller;

import com.example.kdy.user.dto.UserListDTO;
import com.example.kdy.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/userList")
    public String userList(Model model) {
        List<UserListDTO> userList = userService.userList();
        model.addAttribute("userList", userList);

        return "user/user-list";
    }
}
