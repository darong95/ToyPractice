package com.example.kdy.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFileDTO {
    private Long boardSeq; // PK
    private Long boardFileSeq; // FK

    private String bfFileName; // 첨부파일 이름
    private String bfFileUploadName; // 업로드 이름 (UUID + 원본 파일명)
    private String bfFilePath; // 첨부파일 경로
    private String bfFileFullPath; // 첨부파일 전체 경로 (Http로 시작)
}
