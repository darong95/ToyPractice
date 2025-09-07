package com.example.kdy.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardDTO {
    @Schema(hidden = true)
    private Long boardSeq; // 게시판 아이디 (PK)

    @Schema(hidden = true)
    private Long userSeq; // 외래키 (FK)

    @NotBlank(message = "제목을 입력해 주세요.")
    @Schema(description = "게시글 제목", example = "[SWAGGER] 제목입니다.")
    private String boardTitle; // 게시글 제목

    @NotBlank(message = "내용을 입력해 주세요.")
    @Schema(description = "게시글 내용", example = "[SWAGGER] 내용입니다.")
    private String boardContent; // 게시글 내용 : CLOB

    @Schema(hidden = true)
    private String boardRegId; // 게시글 등록 아이디

    // DateEntity
    @Schema(hidden = true)
    private String regDate; // 게시글 등록 날짜

    @Schema(hidden = true)
    private String updateDate; // 게시글 업데이트 날짜
}
