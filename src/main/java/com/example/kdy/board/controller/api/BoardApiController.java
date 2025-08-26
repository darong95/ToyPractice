package com.example.kdy.board.controller.api;

import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService; // Board Domain Service

    @GetMapping("/boardList")
    public Page<BoardEntity> boardList(BoardListDTO boardListDTO, Model model) { // 게시판 리스트
        return boardService.boardList(boardListDTO);
    }

    @DeleteMapping("/boardDeleteOne/{boardSeq}")
    public ResponseEntity<Void> boardDeleteOne(Long boardSeq) { // 단일 삭제
        boardService.boardDeleteOne(boardSeq);
        return ResponseEntity.noContent().build();
    }
}