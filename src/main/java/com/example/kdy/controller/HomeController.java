package com.example.kdy.controller;

import com.example.kdy.dto.menu.MenuDTO;
import com.example.kdy.service.menu.MenuSeparateService;
import com.example.kdy.service.menu.MenuService;

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
    private final MenuService menuService; // Menu Domain Service
    private final MenuSeparateService menuSeparateService; // Menu List를 분리하여 DTO로 반환

    @RequestMapping("/home")
    public String home(Model model) { // 첫 화면
        log.info("[START] The Application is start :)");

        // 전체 메뉴 리스트 가져오기
        List<MenuDTO> menuList = new ArrayList<MenuDTO>();
        menuList = menuService.menuList();

        // 가져온 메뉴를 Depth에 맞게 정리
        Map<String, List<MenuDTO>> separateMenuList = new LinkedHashMap<>();

        if (!menuList.isEmpty()) {
            separateMenuList = menuSeparateService.service(menuList);
        }

        // View로 데이터 전달
        model.addAttribute("menuList", separateMenuList);

        return "index";
    }
}
