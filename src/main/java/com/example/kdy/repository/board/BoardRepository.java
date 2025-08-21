package com.example.kdy.repository.board;

import com.example.kdy.entity.board.BoardEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, JpaSpecificationExecutor<BoardEntity> {}
