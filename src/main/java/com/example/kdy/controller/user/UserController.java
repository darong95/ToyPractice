package com.example.kdy.controller.user;

import com.example.kdy.dto.user.UserDTO;
import com.example.kdy.service.user.UserService;

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
        log.info("[START] Call user List :)");

        // 사용자 전체 리스트 가져오기
        List<UserDTO> userList = userService.userList();

        // View로 데이터 전달
        model.addAttribute("userList", userList);

        return "user/user-list";
    }
}
