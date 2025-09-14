package com.example.kdy.security;

import com.example.kdy.security.service.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static UserPrincipal getLoginUserPrincipal() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getLoginUserSeq() {
        return getLoginUserPrincipal().getUserSeq();
    }

    public static String getLoginUserId() {
        return getLoginUserPrincipal().getUsername();
    }
}
