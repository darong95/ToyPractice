package com.example.kdy.entity.user;

import com.example.kdy.entity.common.DateEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TP_USER")
public class UserEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_SEQ", nullable = false)
    private Long USeq;

    @Column(name = "U_ID", nullable = false, length = 50)
    private String uId;

    @Column(name = "U_NAME", nullable = false, length = 50)
    private String uName;

    @Column(name = "U_PASSWORD", nullable = false)
    private String uPassword;

    @Column(name = "U_EMAIL", nullable = false)
    private String uEmail;

    @Column(name = "U_PHONENUMBER", nullable = false)
    private String uPhoneNumber;
}
