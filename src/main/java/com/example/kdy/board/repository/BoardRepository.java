package com.example.kdy.board.repository;

import com.example.kdy.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, JpaSpecificationExecutor<BoardEntity> {
    @Modifying
    @Query("DELETE FROM BoardEntity bf WHERE bf.boardSeq IN :boardDeleteList")
    void deleteByBoardSeqIn(@Param("boardDeleteList") List<Long> boardDeleteList);
}