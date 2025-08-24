package com.example.kdy.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPagingDTO {
    private int currentPage = 0; // 현재 페이지 (JPA Paging 기준 1페이지 설정은 0)
    private int pagingSize = 5; // 페이징 사이즈 (예) 5, 10...
}
