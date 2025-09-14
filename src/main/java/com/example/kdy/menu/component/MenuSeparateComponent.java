package com.example.kdy.menu.component;

import com.example.kdy.menu.dto.MenuDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Slf4j
@Service
public class MenuSeparateComponent {
    public Map<String, List<MenuDTO>> service(List<MenuDTO> menuList) {
        log.info("[LOG] 메뉴 분류를 시작합니다!");

        // 카테고리 별로 메뉴를 담기 위해 필요한 변수 선언
        Map<String, List<MenuDTO>> seperateMap = new LinkedHashMap<>();
        List<MenuDTO> tempMenuList = new LinkedList<>();

        // 첫 메뉴는 Temp에 바로 넣음
        tempMenuList.add(menuList.get(0));
        long tempParentSeq = menuList.get(0).getMenuSeq();

        // 두 번째 메뉴부터 반복문을 통해 분류함
        for (int i = 1; i < menuList.size(); i++) {
            MenuDTO menuDTO = menuList.get(i); // 현재 메뉴 정보 가져오기

            long currentParentSeq = menuDTO.getMenuParentSeq();
            int mDepth = menuDTO.getMenuDepth(); // Depth 정보

            // 현재 메뉴 정보 : 이전 메뉴를 부모로 가지고 있음 ➡️ 하위 메뉴
            // ParentSeq 값이 있는데 Depth가 0인 경우 ➡️ 카테고리인 경우 :: 메뉴 분류해야 함
            if (tempParentSeq == currentParentSeq && mDepth != 0) {
                tempMenuList.add(menuDTO); // Temp에 추가

            // 현재 메뉴 정보 : 현재 본인이 새로운 부모 메뉴    
            } else {
                seperateMap.put("Parent" + i, tempMenuList); // 기존 Temp 값을 Map에 넣음
                tempParentSeq = menuDTO.getMenuSeq(); // 새로운 부모 메뉴가 나타났으므로 Temp 값 변경

                tempMenuList = new ArrayList<>(); // Temp 재생성
                tempMenuList.add(menuDTO); // Temp 값을 넣었으므로 현재 Index 데이터 값 넣어주기
            }
        }

        // 마지막 값은 Temp에 넣어주지 못 했으므로 수동으로 넣어주기
        seperateMap.put("Parent_End", tempMenuList);
        return seperateMap;
    }
}