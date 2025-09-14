package com.example.kdy.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardDeleteDTO {
    private Long boardSeq; // 게시글 시퀀스 (단일 삭제)
    private Long userSeq; // 작성자 시퀀스

    private List<Long> boardDeleteList; // 삭제 게시글 시퀀스 (일괄 삭제)
    private List<Long> boardDeleteUserList; // 삭제 게시글 사용자 시퀀스 (일괄 삭제)
}
