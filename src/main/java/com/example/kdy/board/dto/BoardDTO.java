package com.example.kdy.board.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {
    private Long boardSeq; // 게시판 아이디 (PK)
    private Long userSeq; // 외래키 (FK)

    @NotBlank(message = "제목을 입력해 주세요.")
    private String boardTitle; // 게시글 제목

    @NotBlank(message = "내용을 입력해 주세요.")
    private String boardContent; // 게시글 내용 : CLOB
    private String boardRegId; // 게시글 등록 아이디

    // DateEntity
    private String regDate; // 게시글 등록 날짜
    private String updateDate; // 게시글 업데이트 날짜
}
