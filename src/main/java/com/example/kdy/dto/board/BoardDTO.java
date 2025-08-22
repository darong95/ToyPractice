package com.example.kdy.dto.board;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {
    private Long bSeq; // 게시판 아이디 (PK)
    private Long USeq; // 외래키 (FK)

    @NotBlank(message = "제목을 입력해 주세요.")
    private String bTitle; // 게시글 제목

    private String bRegId; // 게시글 등록 아이디
    private String bFilePath; // 첨부 파일 경로
    private String bFileName; // 첨부 파일 이름

    @NotBlank(message = "내용을 입력해 주세요.")
    private String bContent; // 게시글 내용 : CLOB

    // DateEntity
    private String regDate; // 게시글 등록 날짜
    private String updateDate; // 게시글 업데이트 날짜
}
