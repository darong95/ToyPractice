package com.example.kdy.board.controller.api;

import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.dto.BoardUpdateDTO;
import com.example.kdy.board.dto.BoardWriteDTO;

import com.example.kdy.board.entity.BoardEntity;

import com.example.kdy.board.service.BoardFileService;
import com.example.kdy.board.service.BoardService;

import com.example.kdy.common.dto.ApiResponse;
import com.example.kdy.security.service.UserPrincipal;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "SwaggerAuth") // 이 Controller의 모든 Method에 적용
public class BoardApiController {
    private final BoardService boardService; // Board Domain Service
    private final BoardFileService boardFileService; // Board File Service

    public ResponseEntity<ApiResponse<Page<BoardEntity>>> boardList(BoardListDTO boardListDTO, Model model) {
        Page<BoardEntity> boardList = boardService.boardList(boardListDTO);
        return ResponseEntity.ok(ApiResponse.success("게시글 리스트를 성공적으로 가져왔습니다.", boardList));
    }

    @PostMapping(value = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> boardWrite(
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

        return ResponseEntity.ok(ApiResponse.success("게시글 작성이 완료되었습니다.", null));
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> boardUpdate(
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

        return ResponseEntity.ok(ApiResponse.success("게시글 수정이 완료되었습니다.", null));
    }

    @DeleteMapping("/delete/{boardSeq}")
    public ResponseEntity<ApiResponse<Void>> boardDeleteOne(@PathVariable Long boardSeq) { // 단일 삭제
        boardFileService.boardFileDeleteBatch(boardSeq);
        boardService.boardDeleteOne(boardSeq);

        return ResponseEntity.ok(ApiResponse.success("게시글이 삭제가 완료되었습니다.", null));
    }
}