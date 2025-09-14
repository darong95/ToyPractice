package com.example.kdy.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.kdy.board.entity.BoardFileEntity;
import org.springframework.data.repository.query.Param;


public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
    void deleteByBoardEntityBoardSeq(Long boardSeq);

    @Modifying
    @Query("DELETE FROM BoardFileEntity bf WHERE bf.boardFileSeq IN :boardFileDeleteList")
    void deleteByBoardFileSeqIn(@Param("boardFileDeleteList") List<Long> boardFileDeleteList); // 게시글 단건 삭제

    @Modifying
    @Query("DELETE FROM BoardFileEntity bf WHERE bf.boardEntity.boardSeq IN :boardDeleteList")
    void deleteByBoardSeqIn(@Param("boardDeleteList") List<Long> boardDeleteList); // 게시글 일괄 삭제

    @Query("SELECT bf FROM BoardFileEntity bf WHERE bf.boardEntity.boardSeq = :boardSeq ORDER BY bf.boardFileSeq ASC")
    List<BoardFileEntity> findBoardFileASC(@Param("boardSeq") Long boardSeq); // 게시글 별 첨부파일 조회
}
