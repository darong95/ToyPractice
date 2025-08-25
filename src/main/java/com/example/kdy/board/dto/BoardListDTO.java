package com.example.kdy.board.dto;

import com.example.kdy.common.dto.PagingDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListDTO extends PagingDTO {
    private BoardDTO boardDTO;// 기본 DTO 변수 추가
}
