package com.example.kdy.service.board;

import com.example.kdy.dto.board.BoardDTO;
import com.example.kdy.dto.board.BoardSearchDTO;

import com.example.kdy.entity.board.BoardEntity;
import com.example.kdy.mapper.board.BoardMapper;
import com.example.kdy.repository.board.BoardRepository;

import com.example.kdy.spec.board.BoardSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public List<BoardDTO> boardList() {
        Sort sort = Sort.by(Sort.Order.asc("bSeq"));
        List<BoardEntity> boardList = boardRepository.findAll(sort);

        return boardMapper.convertToListDTO(boardList);
    }

    public List<BoardDTO> boardSearchList(BoardSearchDTO boardSearchDTO) { // 게시판 상세 검색 (조건 검색)
        // 상세 검색 조건 설정
        Specification<BoardEntity> boardSpecification = Specification.where(null);

        String searchTitle = boardSearchDTO.getBTitle();

        if (searchTitle != null && !searchTitle.isEmpty()) {
            boardSpecification = boardSpecification.and(BoardSpecification.titleContains(searchTitle));
        }

        // 정렬 조건 설정
        Sort sort = Sort.by(Sort.Order.asc("bSeq"));

        // 리스트 가져오기
        List<BoardEntity> boardSearchList = boardRepository.findAll(boardSpecification, sort);

        return boardMapper.convertToListDTO(boardSearchList);
    }

    public BoardDTO writeBoard(BoardDTO boardDTO) {
        log.info("[TEST] U_SEQ : " + boardDTO.getUSeq());

        BoardEntity boardEntity = boardMapper.convertToEntity(boardDTO);
        BoardEntity saveBoardEntity = boardRepository.save(boardEntity); // 게시글 저장

        return boardMapper.convertToDTO(saveBoardEntity);
    }
}
