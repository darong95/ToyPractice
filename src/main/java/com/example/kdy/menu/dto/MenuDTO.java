package com.example.kdy.menu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
    private long mSeq; // 메뉴 아이디 (PK)
    private long mParentSeq; // 상위 메뉴 시퀀스 값

    private int mDepth; // 메뉴의 Depth, 최상단은 0 (Default)
    private int mDepthOrder = 1; // 메뉴의 Depth 기준으로 나열 순서

    private String mName = ""; // 메뉴명
    private String mComment; // 메뉴 설명
    private String mLinkUrl = ""; // 메뉴 URL

    private boolean mCategory = false; // 일반 메뉴가 아닌 카테고리 여부
    private boolean mUseActive = true; // 메뉴 URL
}
