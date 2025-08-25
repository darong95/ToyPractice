package com.example.kdy.menu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
    private long menuSeq; // 메뉴 아이디 (PK)
    private long menuParentSeq; // 상위 메뉴 시퀀스 값

    private int menuDepth; // 메뉴의 Depth, 최상단은 0 (Default)
    private int menuDepthOrder = 1; // 메뉴의 Depth 기준으로 나열 순서

    private String menuName = ""; // 메뉴명
    private String menuComment; // 메뉴 설명
    private String menuLinkUrl = ""; // 메뉴 URL

    private boolean menuCategory = false; // 일반 메뉴가 아닌 카테고리 여부
    private boolean menuUseActive = true; // 메뉴 URL
}
