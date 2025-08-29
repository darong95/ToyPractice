package com.example.kdy.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name="TP_ROLE")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_SEQ", nullable = false)
    private Long roleSeq;

    @Column(name = "R_NAME", nullable = false, length = 50)
    private String roleName;
}
