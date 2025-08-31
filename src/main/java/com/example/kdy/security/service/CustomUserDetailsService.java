package com.example.kdy.security.service;

import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userSeq) throws UsernameNotFoundException {
        Long _userSeq = Long.valueOf(userSeq);

        // 사용자 조회
        UserEntity userEntity = userRepository.findWithRolesByUserSeq(_userSeq)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다 : " + _userSeq));

        /*
        // DB에 담긴 Role 정보를 Security 객체로 변환
        List<SimpleGrantedAuthority> userRoleList = userEntity.getUserRoles().stream()
                .map(userRoleEntity -> new SimpleGrantedAuthority(userRoleEntity.getRoleEntity().getRoleName()))
                .toList();

        // 가져온 정보를 UserDetail에 Setting
        return org.springframework.security.core.userdetails.User.builder()
                .username(String.valueOf(userEntity.getUserSeq())) // Controller ➡️ Authentication.getName()으로 꺼내 쓰기 편함
                .password(userEntity.getUserPassword()) // 실제로는 잘 사용 안 함, UserDetails 구약 대문에 필요함
                .authorities(userRoleList)
                .build();
        */

        return new UserPrincipal(userEntity);
    }
}
