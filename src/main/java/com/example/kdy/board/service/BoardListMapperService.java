package com.example.kdy.board.service;

import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.entity.BoardEntity;

import com.example.kdy.board.mapper.BoardMapper;
import com.example.kdy.common.component.DateUtilComponent;
import com.example.kdy.common.component.ListDateFormatComponent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class BoardListMapperService {
    private final BoardMapper boardMapper;

    private final DateUtilComponent dateUtilComponent;
    private final ListDateFormatComponent listDateFormatComponent;

    public List<BoardListDTO> boardList(List<BoardEntity> boardEntityList) {
        List<BoardListDTO> boardListDTO = boardMapper.convertToLReadListDTO(boardEntityList); // Entity ➡️ ListDTO

        // 날짜 Format
        if (!boardListDTO.isEmpty()) {
            listDateFormatComponent.forListDate(boardListDTO, new BiConsumer<BoardListDTO, DateUtilComponent>() {
                @Override
                public void accept(BoardListDTO boardListDTO, DateUtilComponent dateUtilComponent) { // BiConsumer Override
                    String tempDate = dateUtilComponent.format_String(boardListDTO.getBoardDTO().getRegDate(), "yyyyMMdd");
                    boardListDTO.getBoardDTO().setRegDate(tempDate); // 포맷한 데이터 다시 넣기
                }
            }, dateUtilComponent);
        }

        return boardListDTO;
    }
}
