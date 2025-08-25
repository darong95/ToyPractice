package com.example.kdy.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFileDTO {
    private Long boardSeq; // PK
    private Long boardFileSeq; // FK

    private String boardFileName; // 첨부파일 이름
    private String boardFileUploadName; // 업로드 이름 (UUID + 원본 파일명)
    private String boardFilePath; // 첨부파일 경로
    private String boardFileFullPath; // 첨부파일 전체 경로 (Http로 시작)
}
