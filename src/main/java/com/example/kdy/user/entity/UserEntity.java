package com.example.kdy.user.entity;

import com.example.kdy.common.entity.DateEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TP_USER")
public class UserEntity extends DateEntity {
    // 반드시 이렇게 명시적 getter 작성하거나
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
}