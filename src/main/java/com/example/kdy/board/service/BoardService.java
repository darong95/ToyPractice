package com.example.kdy.board.service;

import com.example.kdy.board.dto.BoardDTO;
import com.example.kdy.board.dto.BoardListDTO;
import com.example.kdy.board.dto.BoardSearchDTO;

import com.example.kdy.board.entity.BoardEntity;
import com.example.kdy.board.mapper.BoardMapper;
import com.example.kdy.board.repository.BoardRepository;

import com.example.kdy.board.spec.BoardSpecification;

import com.example.kdy.common.component.DateUtilComponent;
import com.example.kdy.common.component.ListDateFormatComponent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    private final DateUtilComponent dateUtilComponent;
    private final ListDateFormatComponent listDateFormatComponent;

    public List<BoardListDTO> boardList() {
        Sort sort = Sort.by(Sort.Order.desc("bSeq"));

        List<BoardEntity> boardList = boardRepository.findAll(sort); // Read List
        List<BoardListDTO> boardListDTO = boardMapper.convertToLReadListDTO(boardList); // Entity ➡️ ListDTO

        // 날짜 Format
        if (!boardListDTO.isEmpty()) {
            listDateFormatComponent.forListDate(boardListDTO, new BiConsumer<BoardListDTO, DateUtilComponent>() {
                @Override
                public void accept(BoardListDTO boardListDTO, DateUtilComponent dateUtilComponent) { // BiConsumer Override
                    String tempDate = dateUtilComponent.format_String(boardListDTO.getBoardDTO().getRegDate(), "yyyyMMdd");
                    boardListDTO.getBoardDTO().setRegDate(tempDate); // 포맷한 데이터 다시 넣기
                }
            }, dateUtilComponent);
        }

        return boardListDTO;
    }

    public List<BoardListDTO> boardSearchList(BoardSearchDTO boardSearchDTO) { // 게시판 상세 검색 (조건 검색)
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

        return boardMapper.convertToLReadListDTO(boardSearchList);
    }

    public BoardDTO boardRead(Long bSeq) {
        Optional<BoardEntity> boardOptional = boardRepository.findById(bSeq);

        if (boardOptional.isPresent()) { // 조회 값이 있어야 True
            BoardEntity boardEntity = boardOptional.get();
            return boardMapper.convertToDTO(boardEntity);

        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }

    public BoardDTO boaradWrite(BoardDTO boardDTO) {
        log.info("[TEST] U_SEQ : " + boardDTO.getUSeq());

        BoardEntity boardEntity = boardMapper.convertToEntity(boardDTO);
        BoardEntity saveBoardEntity = boardRepository.save(boardEntity); // 게시글 저장

        return boardMapper.convertToDTO(saveBoardEntity);
    }
}
