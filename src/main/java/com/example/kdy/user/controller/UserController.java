package com.example.kdy.user.controller;

import com.example.kdy.user.dto.UserListDTO;
import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.service.UserListMapperService;
import com.example.kdy.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
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
    private final UserListMapperService userListMapperService;

    @GetMapping("/userList")
    public String userList(UserListDTO userListDTO, Model model) {
        Page<UserEntity> userListPage = userService.userList(userListDTO);
        List<UserListDTO> userList = userListMapperService.userList(userListPage.getContent());

        model.addAttribute("paging", userListPage);
        model.addAttribute("userList", userList);

        return "user/user-list";
    }
}
