package com.example.kdy.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) { // 첫 화면
        log.info("[START] The Application is start :)");
        model.addAttribute("sendText", "JISU IS MY BOYFRIEND");
        return "index";
    }
}
