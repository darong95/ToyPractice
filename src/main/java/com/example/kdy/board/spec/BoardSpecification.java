package com.example.kdy.board.spec;

import com.example.kdy.board.entity.BoardEntity;
import org.springframework.data.jpa.domain.Specification;

public class BoardSpecification {
    // 제목으로 상세 검색
    public static Specification<BoardEntity> titleContains(String boardTitle) {
        return (rootBoardEntity, criteriaQueryquery, criteriaBuilder) -> {
            if (boardTitle == null || boardTitle.isEmpty()) {{
                return null;
            }} // 게시글 정보가 없을 경우 빈 값으로 리턴

            return criteriaBuilder.like(rootBoardEntity.get("boardTitle"), "%" + boardTitle + "%");
        };
    }
}
