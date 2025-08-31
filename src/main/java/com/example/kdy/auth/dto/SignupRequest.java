package com.example.kdy.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "아이디를 입력해 주세요.")
    @Size(min = 5, max = 20, message = "아이디는 5~20자 이내여야 합니다.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()_+]).{8,16}$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~16자리여야 합니다.")
    private String userPassword;

    @NotBlank(message = "비밀번호 확인을 입력해 주세요.")
    private String userRepeatPassword;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String userName;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;

    @NotBlank(message = "휴대전화번호를 입력해 주세요.")
    @Pattern(regexp = "^010[0-9]{8}$", message = "휴대폰 번호는 010으로 시작하는 숫자 11자리여야 합니다.")
    private String userPhoneNumber;
}
