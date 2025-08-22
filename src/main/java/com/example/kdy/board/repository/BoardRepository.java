package com.example.kdy.board.repository;

import com.example.kdy.board.entity.BoardEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, JpaSpecificationExecutor<BoardEntity> {}
