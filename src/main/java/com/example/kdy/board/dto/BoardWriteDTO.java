package com.example.kdy.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardWriteDTO {
    @Schema(description = "게시글 정보")
    @Valid private BoardDTO boardDTO;

    @Schema(description = "첨부 파일 리스트", hidden = true)
    private List<MultipartFile> boardFileList = new ArrayList<>(); // 첨부파일 리스트
}
