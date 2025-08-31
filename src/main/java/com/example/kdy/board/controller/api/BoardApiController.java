package com.example.kdy.board.controller.api;

import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.dto.BoardUpdateDTO;
import com.example.kdy.board.dto.BoardWriteDTO;

import com.example.kdy.board.entity.BoardEntity;

import com.example.kdy.board.service.BoardFileService;
import com.example.kdy.board.service.BoardService;

import com.example.kdy.security.service.UserPrincipal;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

    @PostMapping(value = "/boardWrite", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> boardWrite(
            @Valid @RequestPart("boardWriteDTO") BoardWriteDTO boardWriteDTO
            , @RequestPart(value = "boardFileList", required = false) List<MultipartFile> boardFileList
            , @AuthenticationPrincipal UserPrincipal userPrincipal) {

        // 첨부파일 설정 ➡️ JSON과 MultipartFile은 같은 DTO에 넣을 수 없음
        if (boardFileList != null) {
            boardWriteDTO.setBoardFileList(boardFileList);
        }

        // 게시글 정보 입력
        boardWriteDTO.getBoardDTO().setBoardRegId(userPrincipal.getUsername());
        boardWriteDTO.getBoardDTO().setUserSeq(userPrincipal.getUserSeq());

        // 게시글 & 첨부파일 등록
        Long boardSeq = boardService.boardWrite(boardWriteDTO);
        boardFileService.boardFileWrite(boardSeq, boardWriteDTO.getBoardFileList());

        return ResponseEntity.ok("게시글 등록이 완료되었습니다.");
    }

    @PostMapping(value = "/boardUpdate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> boardUpdate(
            @Valid @RequestPart("boardUpdateDTO") BoardUpdateDTO boardUpdateDTO
            , @RequestPart(value = "boardFileUpdateList", required = false) List<MultipartFile> boardFileUpdateList) {

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
        boardFileService.boardFileDeleteBatch(boardSeq);
        boardService.boardDeleteOne(boardSeq);

        return ResponseEntity.noContent().build();
    }
}