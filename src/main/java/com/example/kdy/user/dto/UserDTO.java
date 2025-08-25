package com.example.kdy.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long userSeq;

    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhoneNumber;

    // DateEntity
    private String regDate; // 게시글 등록 날짜
    private String updateDate; // 게시글 업데이트 날짜
}
