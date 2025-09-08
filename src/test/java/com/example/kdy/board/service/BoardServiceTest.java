package com.example.kdy.board.service;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.dto.BoardWriteDTO;

import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.mapper.BoardMapper;
import com.example.kdy.board.repository.BoardRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private BoardMapper boardMapper;

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void boardCreate() { // Create
        // BoardEntity Setting
        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setBoardSeq(1L);
        boardDTO.setBoardRegId("admin");
        boardDTO.setBoardTitle("[JUnit] JUnit Test");
        boardDTO.setBoardContent("JUnit으로 작성된 게시글입니다.");

        // Service Setting
        BoardWriteDTO boardWriteDTO = new BoardWriteDTO();
        boardWriteDTO.setBoardDTO(boardDTO);

        // BoardEntity Setting ➡️ Repository 실행 후 Return 흉내
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setBoardSeq(1L);
        boardEntity.setBoardRegId("admin");
        boardEntity.setBoardTitle("[JUnit] JUnit Test");
        boardEntity.setBoardContent("JUnit으로 작성된 게시글입니다.");

        // BoardMapper
        when(boardMapper.convertToWriteEntity(boardWriteDTO)).thenReturn(boardEntity);

        // 무조건 SaveEntity 반환
        when(boardRepository.save(any(BoardEntity.class))).thenReturn(boardEntity);

        // Service
        Long boardSeq = boardService.boardWrite(boardWriteDTO);
        Assertions.assertThat(boardSeq).isEqualTo(1L);
    }
}
