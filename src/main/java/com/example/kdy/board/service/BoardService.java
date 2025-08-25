package com.example.kdy.board.service;

import com.example.kdy.board.dto.*;

import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.mapper.BoardMapper;
import com.example.kdy.board.repository.BoardRepository;

import com.example.kdy.board.spec.BoardSpecification;
import com.example.kdy.common.component.DateUtilComponent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;
    private final DateUtilComponent dateUtilComponent;


    public Page<BoardEntity> boardList(BoardListDTO boardListDTO) {
        int currentPage = boardListDTO.getCurrentPage();
        int pagingSize = boardListDTO.getPagingSize();
        log.info("[CURRENT PAGE] :: " + currentPage + ", [PAGING SIZE] :: " + pagingSize);

        Pageable pageable = PageRequest.of(currentPage, pagingSize, Sort.by("boardSeq").descending()); // DESC

        return boardRepository.findAll(pageable);
    }

    public List<BoardListDTO> boardSearchList(BoardSearchDTO boardSearchDTO) { // 게시판 상세 검색 (조건 검색)
        // 상세 검색 조건 설정
        Specification<BoardEntity> boardSpecification = Specification.where(null);

        String searchTitle = boardSearchDTO.getBTitle();

        if (searchTitle != null && !searchTitle.isEmpty()) {
            boardSpecification = boardSpecification.and(BoardSpecification.titleContains(searchTitle));
        }

        // 정렬 조건 설정
        Sort sort = Sort.by(Sort.Order.asc("boardSeq"));

        // 리스트 가져오기
        List<BoardEntity> boardSearchList = boardRepository.findAll(boardSpecification, sort);

        return boardMapper.convertToLReadListDTO(boardSearchList);
    }

    public BoardDTO boardRead(Long boardSeq) {
        Optional<BoardEntity> boardOptional = boardRepository.findById(boardSeq);

        if (boardOptional.isPresent()) { // 조회 값이 있어야 True
            BoardEntity boardEntity = boardOptional.get();
            return boardMapper.convertToDTO(boardEntity);

        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }

    public Long boardWrite(BoardWriteDTO boardWriteDTO) {
        BoardEntity boardEntity = boardMapper.convertToWriteEntity(boardWriteDTO);
        BoardEntity saveBoardEntity = boardRepository.save(boardEntity); // 게시글 저장

        return saveBoardEntity.getBoardSeq();
    }

    public void boardUpdate(BoardUpdateDTO boardUpdateDTO) {
        Optional<BoardEntity> boardOptional = boardRepository.findById(boardUpdateDTO.getBoardSeq());

        if (boardOptional.isPresent()) {
            BoardEntity boardEntity = boardOptional.get();

            // 필요한 필드만 가져와 수정
            boardEntity.setBTitle(boardUpdateDTO.getBTitle());
            boardEntity.setBContent(boardUpdateDTO.getBContent());
            boardEntity.setUpdateDate(dateUtilComponent.getCurrentDate_MICRO());

            // 게시글 수정
            BoardEntity updateEntity = boardRepository.save(boardEntity);

        } else {
            throw new RuntimeException("수정할 게시글이 존재하지 않습니다.");
        }
    }

    public void boardDeleteOne(Long boardSeq) {
        Optional<BoardEntity> boardOptional = boardRepository.findById(boardSeq);

        if (boardOptional.isPresent()) {
            BoardEntity boardEntity = boardOptional.get();
            boardRepository.delete(boardEntity); // 게시글 삭제

        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }
}
