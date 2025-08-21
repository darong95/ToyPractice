package com.example.kdy.controller.board;

import com.example.kdy.dto.board.BoardDTO;
import com.example.kdy.dto.board.BoardSearchDTO;
import com.example.kdy.service.board.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService; // Board Domain Service

    @GetMapping("/boardList")
    public String boardList(Model model) { // 게시판 리스트
        log.info("[START] Call board List");

        // 게시판 전체 리스트 가져오기
        List<BoardDTO> boardList = new ArrayList<>();
        boardList = boardService.boardList();

        // View로 데이터 전달
        model.addAttribute("boardList", boardList);

        return "/board/board-list";
    }

    @GetMapping("/boardSearchList")
    public String boardList(Model model, BoardSearchDTO boardSearchDTO) { // 게시판 리스트 (상세 검색)
        // 게시판 전체 리스트 가져오기
        List<BoardDTO> boardSearchList = new ArrayList<>();
        boardSearchList = boardService.boardSearchList(boardSearchDTO);

        // View로 데이터 전달
        model.addAttribute("boardList", boardSearchList);

        return "/board/boardList";
    }
}
