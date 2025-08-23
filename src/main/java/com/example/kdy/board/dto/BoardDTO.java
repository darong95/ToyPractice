package com.example.kdy.board.dto;

import com.example.kdy.board.dto.validation.BoardValidationDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO extends BoardValidationDTO {
    private Long bSeq; // 게시판 아이디 (PK)
    private Long USeq; // 외래키 (FK)

    private String bRegId; // 게시글 등록 아이디
    private String bFilePath; // 첨부 파일 경로
    private String bFileName; // 첨부 파일 이름

    // DateEntity
    private String regDate; // 게시글 등록 날짜
    private String updateDate; // 게시글 업데이트 날짜
}
