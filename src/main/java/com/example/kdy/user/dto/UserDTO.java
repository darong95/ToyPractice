package com.example.kdy.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long USeq;

    private String uId;
    private String uName;
    private String uPassword;
    private String uEmail;
    private String uPhoneNumber;

    // DateEntity
    private String regDate; // 게시글 등록 날짜
    private String updateDate; // 게시글 업데이트 날짜
}
