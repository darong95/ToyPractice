package com.example.kdy.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListDTO extends BoardPagingDTO {
    private BoardDTO boardDTO;// 기본 DTO 변수 추가
}
