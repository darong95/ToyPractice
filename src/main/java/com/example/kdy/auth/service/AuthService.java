package com.example.kdy.auth.service;

import com.example.kdy.auth.dto.LoginRequest;
import com.example.kdy.auth.dto.SignupRequest;

import com.example.kdy.security.jwt.JwtTokenProvider;
import com.example.kdy.user.entity.RoleEntity;
import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.entity.UserRoleEntity;

import com.example.kdy.user.repository.RoleRepository;
import com.example.kdy.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(SignupRequest signupRequest) { // 회원 가입
        // 가입 여부 확인
        if (userRepository.findByUserId(signupRequest.getUserId()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        // 가입 된 사용자가 아닐 경우 신규 가입 처리
        UserEntity userEntity = UserEntity.builder()
                .userId(signupRequest.getUserId())
                .userPassword(passwordEncoder.encode(signupRequest.getUserPassword()))
                .userName(signupRequest.getUserName())
                .userEmail(signupRequest.getUserEmail())
                .userPhoneNumber(signupRequest.getUserPhoneNumber())
                .build();

        // 사용자 권한 확인
        RoleEntity roleEntity = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("기본 권한이 존재하지 않습니다."));

        // 사용자 & 권한 관계 주입
        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .userEntity(userEntity)
                .roleEntity(roleEntity)
                .build();

        userEntity.getUserRoles().add(userRoleEntity);
        
        // DB에 저장
        userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    public String login(LoginRequest loginRequest) { // 로그인 ➡️ JWT 발급
        // 아이디 조회
        UserEntity userEntity = userRepository.findWithRolesByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        // 비밀번호 조회
        String password_Encode = userEntity.getUserPassword();
        String password_Request = loginRequest.getUserPassword();

        if (!passwordEncoder.matches(password_Request, password_Encode)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT ➡️ Subject : UserId, Claim : UserId
        return jwtTokenProvider.generateToken(userEntity.getUserSeq(), userEntity.getUserId());
    }
}
