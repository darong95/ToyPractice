package com.example.kdy.user.service;

import com.example.kdy.common.component.AsteriskUtilComponent;
import com.example.kdy.common.component.DateUtilComponent;
import com.example.kdy.common.component.IF.AccessorNameInterface;
import com.example.kdy.common.component.ListDateFormatComponent;

import com.example.kdy.user.dto.UserListDTO;
import com.example.kdy.user.entity.UserEntity;
import com.example.kdy.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class UserListMapperService {
    private final UserMapper userMapper;

    private final DateUtilComponent dateUtilComponent;
    private final ListDateFormatComponent listDateFormatComponent;
    private final AsteriskUtilComponent asteriskUtilComponent;

    public List<UserListDTO> userList(List<UserEntity> userEntityList) {
        List<UserListDTO> userListDTO = userMapper.convertToReadListDTO(userEntityList); // Entity ➡️ ListDTO

        if (!userListDTO.isEmpty()) {
            // Asterisk 처리 (이름)
            asteriskUtilComponent.asterisk_Name_List(userListDTO, new AccessorNameInterface<UserListDTO>() {
                @Override
                public String getAccessName(UserListDTO userListDTO) {
                    return userListDTO.getUserDTO().getUserName();
                }

                @Override
                public void setAccessName(UserListDTO userListDTO, String settingName) {
                    userListDTO.getUserDTO().setUserName(settingName);
                }
            });

            // 날짜 출력 Format
            listDateFormatComponent.forListDate(userListDTO, new BiConsumer<UserListDTO, DateUtilComponent>() {
                @Override
                public void accept(UserListDTO boardListDTO, DateUtilComponent dateUtilComponent) { // BiConsumer Override
                    String tempDate = dateUtilComponent.format_String(boardListDTO.getUserDTO().getRegDate(), "yyyyMMdd");
                    boardListDTO.getUserDTO().setRegDate(tempDate); // 포맷한 데이터 다시 넣기
                }

            }, dateUtilComponent);
        }

        return userListDTO;
    }
}
