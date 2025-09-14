package com.example.kdy.user.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "TP_USER_ROLE")
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UR_SEQ")
    private Long userRoleSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_SEQ")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "R_SEQ")
    private RoleEntity roleEntity;
}
