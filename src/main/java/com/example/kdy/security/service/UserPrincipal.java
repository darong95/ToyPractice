package com.example.kdy.security.service;

import com.example.kdy.user.entity.UserEntity;
import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal implements UserDetails {
    private final Long userSeq; // 사용자 시퀀스

    private final String username; // 사용자 아이디 (Security 고정 변수명)
    private final String userNickName; // 사용자 이름 (추가 변수)
    private final String password; // 사용자 비밀번호 (Security 고정 변수명)

    private final List<GrantedAuthority> authorities; // 권한 리스트 (Roles)

    // 생성자
    public UserPrincipal(UserEntity userEntity) {
        this.userSeq = userEntity.getUserSeq();
        this.username = userEntity.getUserId();
        this.userNickName = userEntity.getUserName();
        this.password = userEntity.getUserPassword();

        this.authorities = userEntity.getUserRoles().stream()
                .map(userRole -> (GrantedAuthority) () -> userRole.getRoleEntity().getRoleName())
                .collect(Collectors.toList());
    }

    // 데이터 접근 Getter
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
