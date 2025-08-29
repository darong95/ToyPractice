package com.example.kdy.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name="TP_ROLE")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "R_SEQ", nullable = false)
    private Long roleSeq;

    @Column(name = "R_NAME", nullable = false, length = 50)
    private String roleName;
}
