package com.example.kdy.board.dto.validation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardValidationDTO {
    @NotBlank(message = "제목을 입력해 주세요.")
    private String bTitle; // 게시글 제목

    @NotBlank(message = "내용을 입력해 주세요.")
    private String bContent; // 게시글 내용 : CLOB
}
