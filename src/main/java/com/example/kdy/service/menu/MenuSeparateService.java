package com.example.kdy.service.menu;

import com.example.kdy.entity.menu.MenuEntity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

@Slf4j
@Service
public class MenuSeparateService {
    public Map<String, List<MenuEntity>> service(List<MenuEntity> menuList) {
        log.info("[LOG] 메뉴 분류를 시작합니다!");
        
        // 카테고리 별로 메뉴를 담기 위해 필요한 변수 선언
        Map<String, List<MenuEntity>> seperateMap = new LinkedHashMap<>();
        List<MenuEntity> tempMenuList = new LinkedList<>();

        // 첫 메뉴는 Temp에 바로 넣음
        tempMenuList.add(menuList.get(0));
        long tempParentSeq = menuList.get(0).getMSeq();

        // 두 번째 메뉴부터 반복문을 통해 분류함
        for (int i = 1; i < menuList.size(); i++) {
            MenuEntity menuEntity = menuList.get(i); // 현재 메뉴 정보 가져오기

            long currentParentSeq = menuEntity.getMParentSeq();
            int mDepth = menuEntity.getMDepth(); // Depth 정보

            // 현재 메뉴 정보 : 이전 메뉴를 부모로 가지고 있음 ➡️ 하위 메뉴
            // ParentSeq 값이 있는데 Depth가 0인 경우 ➡️ 카테고리인 경우 :: 메뉴 분류해야 함
            if (tempParentSeq == currentParentSeq && mDepth != 0) {
                tempMenuList.add(menuEntity); // Temp에 추가

            // 현재 메뉴 정보 : 현재 본인이 새로운 부모 메뉴    
            } else {
                seperateMap.put("Parent" + i, tempMenuList); // 기존 Temp 값을 Map에 넣음
                tempParentSeq = menuEntity.getMSeq(); // 새로운 부모 메뉴가 나타났으므로 Temp 값 변경

                tempMenuList = new ArrayList<>(); // Temp 재생성
                tempMenuList.add(menuEntity); // Temp 값을 넣었으므로 현재 Index 데이터 값 넣어주기
            }
        }

        // 마지막 값은 Temp에 넣어주지 못 했으므로 수동으로 넣어주기
        seperateMap.put("Parent_End", tempMenuList);
        return seperateMap;
    }
}