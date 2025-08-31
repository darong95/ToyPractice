package com.example.kdy.board.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardWriteDTO {
    @Valid private BoardDTO boardDTO; //
    private List<MultipartFile> boardFileList = new ArrayList<>(); // 첨부파일 리스트
}
