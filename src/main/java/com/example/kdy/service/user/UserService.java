package com.example.kdy.service.user;

import com.example.kdy.dto.user.UserDTO;
import com.example.kdy.entity.user.UserEntity;
import com.example.kdy.mapper.user.UserMapper;
import com.example.kdy.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDTO> userList() {
        Sort sort = Sort.by(Sort.Order.desc("uSeq"));
        List<UserEntity> userList = userRepository.findAll(sort);

        return userMapper.convertToListDTO(userList);
    }
}
