package com.example.kdy.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class DateEntity {
    @Column(name = "REG_DATE", nullable = false, updatable = false)
    private LocalDateTime regDate; // 등록 시간

    @Column(name = "UPDATE_DATE", nullable = false, updatable = false)
    private LocalDateTime updateDate; // 업데이트 시간

    @PrePersist
    protected void settingRegDate() {
        this.regDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }
}
