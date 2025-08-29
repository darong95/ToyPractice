package com.example.kdy.user.entity;

import com.example.kdy.common.entity.DateEntity;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder // 회원 가입 구현할 때 필요
@AllArgsConstructor // Builder와 함께 쓰기 위해 필요함
@NoArgsConstructor // 기본 생성자를 사용하기 위해 필요 (Builder를 사용하면 기본 생성자를 자동으로 안 만들어줌)
@Table(name = "TP_USER")
public class UserEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_SEQ", nullable = false)
    private Long userSeq;

    @Column(name = "U_ID", nullable = false, length = 50)
    private String userId;

    @Column(name = "U_NAME", nullable = false, length = 50)
    private String userName;

    @Column(name = "U_PASSWORD", nullable = false)
    private String userPassword;

    @Column(name = "U_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "U_PHONENUMBER", nullable = false)
    private String userPhoneNumber;

    @Builder.Default
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserRoleEntity> userRoles = new ArrayList<>();
}