package com.example.kdy.board.dto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Getter
@Setter
public class BoardWriteDTO extends BoardDTO {
    private List<MultipartFile> boardFileList; // 첨부파일 리스트
}
