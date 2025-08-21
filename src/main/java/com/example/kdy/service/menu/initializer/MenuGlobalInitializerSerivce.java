package com.example.kdy.service.menu.initializer;

import com.example.kdy.dto.menu.MenuDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice // 모든 컨트롤러에 적용
@AllArgsConstructor
public class MenuGlobalInitializerSerivce {
    private final MenuSettingService menuSettingService;

    @ModelAttribute("menuList")
    public Map<String, List<MenuDTO>> menuGlobalInitializer() {
        return menuSettingService.getMenu();
    }
}
