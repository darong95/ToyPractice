package com.example.kdy.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private long bSeq; // 게시판 아이디 (PK)
    private long uSeq; // 외래키 (FK)

    private String bTitle; // 게시글 제목
    private String bRegId; // 게시글 등록 아이디
    private String bFilePath; // 첨부 파일 경로
    private String bFileName; // 첨부 파일 이름
    private String bContent; // 게시글 내용 : CLOB

    // DateEntity
    private String regDate; // 게시글 등록 날짜
    private String updateDate; // 게시글 업데이트 날짜
}
