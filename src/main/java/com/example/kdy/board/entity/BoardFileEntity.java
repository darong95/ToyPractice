package com.example.kdy.board.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TP_BOARD_FILE")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BF_SEQ", nullable = false)
    private Long boardFileSeq; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="B_SEQ", nullable = false, updatable = false)
    private BoardEntity boardEntity; // FK

    @Column(name="BF_FILE_NAME", nullable = false)
    private String boardFileName; // 첨부파일 이름

    @Column(name="BF_FILE_UPLOAD_NAME", nullable = false)
    private String boardFileUploadName;

    @Column(name="BF_FILE_PATH", nullable = false)
    private String boardFilePath; // 첨부파일 경로

    @Column(name="BF_FILE_FULL_PATH", nullable = false)
    private String boardFileFullPath; // 첨부파일 전체 경로 (Http로 시작)
}
