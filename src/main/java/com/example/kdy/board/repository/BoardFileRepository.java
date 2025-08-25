package com.example.kdy.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.kdy.board.entity.BoardFileEntity;
import org.springframework.data.repository.query.Param;


public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
    @Query("SELECT bf FROM BoardFileEntity bf WHERE bf.boardEntity.boardSeq = :boardSeq ORDER BY bf.boardFileSeq ASC")
    List<BoardFileEntity> findByBoardBoardSeqOrderByBoardFileSeq(@Param("boardSeq") Long boardSeq);
}
