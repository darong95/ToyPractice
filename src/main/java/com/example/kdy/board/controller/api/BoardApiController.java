package com.example.kdy.board.controller.api;

import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.dto.BoardUpdateDTO;
import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.service.BoardFileService;
import com.example.kdy.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService; // Board Domain Service
    private final BoardFileService boardFileService; // Board File Service

    @GetMapping("/boardList")
    public Page<BoardEntity> boardList(BoardListDTO boardListDTO, Model model) { // 게시판 리스트
        return boardService.boardList(boardListDTO);
    }

    @PostMapping(value = "/boardUpdate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> boardUpdate(
            @Valid @RequestPart("boardUpdateDTO") BoardUpdateDTO boardUpdateDTO
            , @RequestPart(value = "boardFileUpdateList", required = false) List<MultipartFile> boardFileUpdateList
            , BindingResult bindingResult) {

        // 첨부파일 설정 ➡️ JSON과 MultipartFile은 같은 DTO에 넣을 수 없음
        if (boardFileUpdateList != null) {
            boardUpdateDTO.setBoardFileUpdateList(boardFileUpdateList);
        }

        // 데이터 처리 (Service)
        boardService.boardUpdate(boardUpdateDTO);
        boardFileService.boardFileDeleteIn(boardUpdateDTO.getBoardFileDeleteList());
        boardFileService.boardFileWrite(boardUpdateDTO.getBoardDTO().getBoardSeq(), boardUpdateDTO.getBoardFileUpdateList());

        return ResponseEntity.ok("게시글 수정이 완료되었습니다.");
    }

    @DeleteMapping("/boardDeleteOne/{boardSeq}")
    public ResponseEntity<Void> boardDeleteOne(@PathVariable Long boardSeq) { // 단일 삭제
        boardService.boardDeleteOne(boardSeq);
        return ResponseEntity.noContent().build();
    }
}