package com.example.kdy.entity.menu;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "TP_MENU",
        indexes = {
        @Index(name = "M_PARENT_SEQ", columnList = "mParentSeq"),
        @Index(name = "M_DEPTH_ORDER", columnList = "mDepthOrder")
})
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_SEQ", nullable = false)
    private long mSeq; // 메뉴 아이디 (PK)

    @Column(name = "M_PARENT_SEQ", nullable = false)
    private long mParentSeq; // 상위 메뉴 시퀀스 값

    @Column(name = "M_DEPTH", nullable = false)
    private int mDepth; // 메뉴의 Depth, 최상단은 0 (Default)

    @Column(name = "M_DEPTH_ORDER", nullable = false)
    private int mDepthOrder = 1; // 메뉴의 Depth 기준으로 나열 순서

    @Column(name = "M_NAME", nullable = false, length = 50)
    private String mName = ""; // 메뉴명

    private String mComment; // 메뉴 설명
    private String mLinkUrl = ""; // 메뉴 URL

    @Column(name = "M_CATEGORY", nullable = true, length = 50)
    private boolean mCategory = false; // 일반 메뉴가 아닌 카테고리 여부

    @Column(name = "M_USE_ACTIVE", nullable = false)
    private boolean mUseActive = true; // 메뉴 URL
}