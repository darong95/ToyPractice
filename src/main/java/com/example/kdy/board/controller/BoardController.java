package com.example.kdy.board.controller;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.dto.BoardSearchDTO;

import com.example.kdy.board.dto.BoardUpdateDTO;
import com.example.kdy.board.service.BoardService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService; // Board Domain Service

    @GetMapping("/boardList")
    public String boardList(Model model) { // 게시판 리스트
        List<BoardListDTO> boardList = boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);

        return "board/board-list";
    }

    @GetMapping("/boardSearchList")
    public String boardSearchList(Model model, BoardSearchDTO boardSearchDTO) { // 게시판 리스트 (상세 검색)
        List<BoardListDTO> boardSearchList = boardService.boardSearchList(boardSearchDTO);
        model.addAttribute("boardList", boardSearchList);

        return "board/boardList";
    }

    @GetMapping("/boardDetail/{bSeq}")
    public String boardDetail(@PathVariable Long bSeq, Model model) { // 게시판 상세 내용
        BoardDTO boardDTO = boardService.boardRead(bSeq);
        model.addAttribute("boardPost", boardDTO);

        return "board/board-detail";
    }

    @GetMapping("/boardWriteForm")
    public String boardWriteForm(Model model) { // 게시판 신규 작성 화면
        model.addAttribute("boardPost", new BoardDTO());
        return "board/board-writeForm";
    }

    @PostMapping("/boardWrite")
    public String boardWrite(@Valid @ModelAttribute("boardPost") BoardDTO boardDTO, BindingResult bindingResult) { // 게시판 신규 작성 (저장)
        // 에러가 있을 경우 작성 폼으로 이동 ➡️ 내용을 보여주기 위함
        if (bindingResult.hasErrors()) {
            return "board/board-writeForm";
        }

        // 게시글 등록 : 필수 데이터 하드 코딩 (추후 업데이트 예정)
        boardDTO.setBRegId("admin");
        boardDTO.setUSeq(1L);

        boardService.boaradWrite(boardDTO);

        return "redirect:/board/boardList";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(@Valid @ModelAttribute("boardPost") BoardUpdateDTO boardUpdateDTO, BindingResult bindingResult) {
        System.out.println("check :: controller :: " + boardUpdateDTO.getBSeq() + ", " + boardUpdateDTO.getBRegId());

        // 게시판 수정 : 에러가 있을 경우 작성 폼으로 이동 ➡️ 내용을 보여주기 위함
        if (bindingResult.hasErrors()) {
            return "board/board-detail";
        }

        // 게시글 수정
        boardService.boaradUpdate(boardUpdateDTO);

        return "redirect:/board/boardList";
    }
}
