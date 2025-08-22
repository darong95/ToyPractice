package com.example.kdy.entity.board;

import com.example.kdy.entity.common.DateEntity;
import com.example.kdy.entity.user.UserEntity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TP_BOARD")
public class BoardEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="B_SEQ", nullable = false)
    private Long bSeq; // 게시판 아이디 (PK)

    @ManyToOne // 다대 일 관계 ➡️ U_SEQ는 B_SEQ를 여러개 가질 수 있음
    @JoinColumn(name="U_SEQ", nullable = false)
    private UserEntity userEntity; // 외래키 (FK)

    @Column(name = "B_TITLE", nullable = false)
    private String bTitle; // 게시글 제목

    @Column(name = "B_REG_ID", nullable = false)
    private String bRegId; // 게시글 등록 아이디

    private String bFilePath; // 첨부 파일 경로
    private String bFileName; // 첨부 파일 이름

    @Lob
    @Column(name = "B_CONTENT", nullable = false)
    private String bContent; // 게시글 내용 : CLOB
}
