package com.example.kdy.user.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.example.kdy.user.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUserId(String userId); // UserId 조회

    @EntityGraph(attributePaths = {"userRoles", "userRoles.roleEntity"})
    Optional<UserEntity> findWithRolesByUserSeq(Long userSeq); // UserSeq로 Role 조회

    @EntityGraph(attributePaths = {"userRoles", "userRoles.roleEntity"})
    Optional<UserEntity> findWithRolesByUserId(String userId); // UserId로 Role 조회
}
