package com.example.kdy.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "아이디를 입력해 주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String userPassword;

    @NotBlank(message = "비밀번호 확인을 입력해 주세요.")
    private String userRepeatPassword;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String userName;

    @NotBlank(message = "이메일을 입력해 주세요.")
    private String userEmail;

    @NotBlank(message = "휴대전화번호를 입력해 주세요.")
    private String userPhoneNumber;
}
