package com.example.kdy.menu.component;

import com.example.kdy.menu.dto.MenuDTO;
import com.example.kdy.menu.service.MenuService;

import com.example.kdy.security.SecurityUtil;
import jakarta.annotation.PostConstruct;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class MenuSettingComponent {
    private final MenuService menuService; // Menu Domain Service
    private final MenuSeparateComponent menuSeparateComponent; // Menu List를 분리하여 DTO로 반환

    private List<MenuDTO> tempMenuList; // 메뉴를 담기 위한 List

    @PostConstruct // 초기화 설정을 위해 딱 한 번 실행 됨
    private void menuLoadAndSetting_Public() { // 메뉴 초기화
        log.info("[PRE] MENU LOADING AND SETTING :: PUBLIC");
        tempMenuList = menuService.menuList(); // 전체 리스트 가져오기 (= Admin)
    }

    public Map<String, List<MenuDTO>> getMenu() {
        List<MenuDTO> menuList = new ArrayList<>();
        Map<String, List<MenuDTO>> separateMenuList = new LinkedHashMap<>(); // 메뉴를 담기 위한 Map

        try {
            // 로그인 헀을 경우 권한에 맞게 메뉴 다시 가져오기
            if (!SecurityUtil.isAnonymous()) {
                menuList = menuService.menuRolesList(SecurityUtil.getLoginUserRoles());

            } else {
                menuList = tempMenuList; // 로그인 정보가 없을 경우 기본 메뉴
            }

            // 가져온 메뉴를 Depth에 맞게 정리
            if (!menuList.isEmpty()) {
                separateMenuList = menuSeparateComponent.service(menuList);
            }

        } catch (Exception e) {
            log.error("MenuSetting is Fail", e);
            throw e;  // 예외를 다시 던져 빈 생성 실패 원인으로 남기기
        }

        return separateMenuList;
    }
}
