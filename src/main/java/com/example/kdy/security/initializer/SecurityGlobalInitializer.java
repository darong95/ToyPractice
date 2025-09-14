package com.example.kdy.security.initializer;

import com.example.kdy.security.SecurityUtil;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice // 모든 컨트롤러에 적용
public class SecurityGlobalInitializer {
    @ModelAttribute("loginUserId")
    public String globalInitializer_LoginUserId() {
        // 로그인 안 된 상태 혹은 익명 사용자 처리
        if (SecurityUtil.isAnonymous()) {
            return "Guest"; // 또는 null, 빈 문자열 등 정책에 맞게 처리
        }

        return SecurityUtil.getLoginUserPrincipal().getUsername();
    }

    @ModelAttribute("loginUserName")
    public String globalInitializer_LoginUserName() {
        // 로그인 안 된 상태 혹은 익명 사용자 처리
        if (SecurityUtil.isAnonymous()) {
            return "게스트"; // 또는 null, 빈 문자열 등 정책에 맞게 처리
        }

        return SecurityUtil.getLoginUserPrincipal().getUserNickName();
    }
}
