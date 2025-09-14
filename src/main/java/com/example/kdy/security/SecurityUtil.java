package com.example.kdy.security;

import com.example.kdy.security.service.UserPrincipal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUtil {
    public static UserPrincipal getLoginUserPrincipal() { // 로그인 된 사용자 정보 가져오기
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getLoginUserSeq() { // 사용자 시퀀스
        return getLoginUserPrincipal().getUserSeq();
    }

    public static String getLoginUserId() { // 사용자 이름
        return getLoginUserPrincipal().getUsername();
    }

    public static List<String> getLoginUserRoles() { // 사용자 이름
        return getLoginUserPrincipal().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public static boolean isAnonymous() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal());
    }
}
