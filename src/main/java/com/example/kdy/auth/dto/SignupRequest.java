package com.example.kdy.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SignupRequest {
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhoneNumber;
}
