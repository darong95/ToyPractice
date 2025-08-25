package com.example.kdy.board.controller;

import com.example.kdy.board.dto.*;

import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.service.BoardFileService;
import com.example.kdy.board.service.BoardListMapperService;
import com.example.kdy.board.service.BoardService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService; // Board Domain Service
    private final BoardFileService boardFileService; // Board File Service
    private final BoardListMapperService boardListMapperService; // Board List Mapper Service

    @GetMapping("/boardList")
    public String boardList(BoardListDTO boardListDTO, Model model) { // 게시판 리스트
        Page<BoardEntity> boardListPage = boardService.boardList(boardListDTO);
        List<BoardListDTO> boardList = boardListMapperService.boardList(boardListPage.getContent());

        model.addAttribute("paging", boardListPage);
        model.addAttribute("boardList", boardList);

        return "board/board-list";
    }

    @GetMapping("/boardSearchList")
    public String boardSearchList(Model model, BoardSearchDTO boardSearchDTO) { // 게시판 리스트 (상세 검색)
        List<BoardListDTO> boardSearchList = boardService.boardSearchList(boardSearchDTO);
        model.addAttribute("boardList", boardSearchList);

        return "board/boardList";
    }

    @GetMapping("/boardDetail/{boardSeq}")
    public String boardDetail(@PathVariable Long boardSeq, Model model) { // 게시판 상세 내용
        BoardDTO boardDTO = boardService.boardRead(boardSeq);
        List<BoardFileDTO> boardFileList = boardFileService.boardFileList(boardSeq);

        model.addAttribute("boardPost", boardDTO);
        model.addAttribute("boardFileList", boardFileList);

        return "board/board-detail";
    }

    @GetMapping("/boardWriteForm")
    public String boardWriteForm(Model model) { // 게시판 신규 작성 화면
        model.addAttribute("boardPost", new BoardWriteDTO());
        return "board/board-writeForm";
    }

    @PostMapping("/boardWrite")
    public String boardWrite(@Valid @ModelAttribute("boardPost") BoardWriteDTO boardWriteDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 에러가 있을 경우 작성 폼으로 이동 ➡️ 내용을 보여주기 위함
        if (bindingResult.hasErrors()) {
            return "board/board-writeForm";
        }

        // 게시글 등록 & 첨부 파일 등록: 필수 데이터 하드 코딩 (추후 업데이트 예정)
        boardWriteDTO.setBRegId("admin");
        boardWriteDTO.setUserSeq(1L);

        Long boardSeq = boardService.boardWrite(boardWriteDTO);
        boardFileService.boardFileWrite(boardSeq, boardWriteDTO.getBoardFileList());

        redirectAttributes.addFlashAttribute("resultMessage", "게시글 등록이 완료되었습니다.");

        return "redirect:/board/boardList";
    }

    @GetMapping("/boardUpdateForm/{boardSeq}")
    public String boardUpdateForm(@PathVariable Long boardSeq, Model model) { // 게시판 상세 내용
        BoardDTO boardDTO = boardService.boardRead(boardSeq);
        List<BoardFileDTO> boardFileList = boardFileService.boardFileList(boardSeq);

        model.addAttribute("boardPost", boardDTO);
        model.addAttribute("boardFileList", boardFileList);

        return "board/board-updateForm";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(@Valid @ModelAttribute("boardPost") BoardUpdateDTO boardUpdateDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 에러가 있을 경우 작성 폼으로 이동 ➡️ 내용을 보여주기 위함
        if (bindingResult.hasErrors()) {
            return "board/board-detail";
        }

        boardService.boardUpdate(boardUpdateDTO);
        redirectAttributes.addFlashAttribute("resultMessage", "게시글 수정이 완료되었습니다.");

        return "redirect:/board/boardList";
    }

    @GetMapping("/boardDeleteOne/{boardSeq}")
    public String boardUpdate(@PathVariable Long boardSeq, RedirectAttributes redirectAttributes) { // 게시글 삭제 (단일)
        boardFileService.boardFileDeleteBatch(boardSeq);
        boardService.boardDeleteOne(boardSeq);
        redirectAttributes.addFlashAttribute("resultMessage", "게시글 삭제가 완료되었습니다.");

        return "redirect:/board/boardList";
    }

    @GetMapping("/boardFileDownload/{boardFileSeq}")
    public void boardFileDownload(@PathVariable Long boardFileSeq, HttpServletResponse httpServletResponse) {
        boardFileService.boardFileDownload(boardFileSeq, httpServletResponse);
    }
}
