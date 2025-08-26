package com.example.kdy.board.dto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardUpdateDTO {
    private BoardDTO boardDTO;

    private List<Long> boardFileDeleteList = new ArrayList<>(); // 첨부파일 삭제 시퀀스 (ID)
    private List<MultipartFile> boardFileUpdateList; // 첨부파일 리스트
}
