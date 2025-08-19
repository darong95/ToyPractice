package com.example.kdy.repository.menu;

import com.example.kdy.entity.menu.MenuEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> { }
