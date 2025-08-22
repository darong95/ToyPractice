package com.example.kdy.menu.service.initializer;

import com.example.kdy.menu.dto.MenuDTO;
import com.example.kdy.menu.service.MenuSeparateService;
import com.example.kdy.menu.service.MenuService;

import jakarta.annotation.PostConstruct;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class MenuSettingService {
    private final MenuService menuService; // Menu Domain Service
    private final MenuSeparateService menuSeparateService; // Menu List를 분리하여 DTO로 반환

    private Map<String, List<MenuDTO>> separateMenuList; // 메뉴를 담기 위한 Map

    @PostConstruct // 초기화 설정을 위해 딱 한 번 실행 됨
    private void menuLoadAndSetting() { // 메뉴 초기화
        try {
            // 메뉴 가져오기
            List<MenuDTO> menuList = new ArrayList<MenuDTO>();
            menuList = menuService.menuList();

            // 가져온 메뉴를 Depth에 맞게 정리
            if (!menuList.isEmpty()) {
                separateMenuList = menuSeparateService.service(menuList);
            }

        } catch (Exception e) {
            log.error("menuLoadAndSetting 초기화 실패", e);
            throw e;  // 예외를 다시 던져 빈 생성 실패 원인으로 남기기
        }
    }

    public Map<String, List<MenuDTO>> getMenu() {
        return separateMenuList;
    }
}
