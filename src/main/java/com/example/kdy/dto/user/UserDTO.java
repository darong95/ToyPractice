package com.example.kdy.dto.user;

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
}
