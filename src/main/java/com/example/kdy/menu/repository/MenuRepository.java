package com.example.kdy.menu.repository;

import com.example.kdy.menu.entity.MenuEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    @Query("SELECT m FROM MenuEntity m WHERE m.menuRole IN :userRoles ORDER BY m.menuParentSeq, m.menuDepthOrder ASC")
    List<MenuEntity> findByMenuRole(@Param("userRoles") List<String> userRoles);
}
