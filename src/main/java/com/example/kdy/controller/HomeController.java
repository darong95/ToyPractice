package com.example.kdy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    @RequestMapping("/home")
    public String home(Model model) { // 첫 화면
        log.info("[START] The Application is start :)");
        return "index";
    }
}
