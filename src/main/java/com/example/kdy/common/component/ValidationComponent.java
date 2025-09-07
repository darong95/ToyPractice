package com.example.kdy.common.component;

import com.example.kdy.common.exception.CustomValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationComponent {
    private final Validator validator;

    public <T> void validation_Check(T checkDTO) {
        // CheckDTO의 모든 Valid 조건을 체크함
        Set<ConstraintViolation<T>> violations = validator.validate(checkDTO);

        // 작성한 유효성 검사가 있을 경우
        if (!violations.isEmpty()) {
            Map<String, String> errorMap = new LinkedHashMap<>();

            for (ConstraintViolation<T> violation : violations) {
                String map_Field = violation.getPropertyPath().toString(); // 예: boardDTO.boardTitle
                String map_Message = violation.getMessage(); // 예: 제목을 입력해주세요.

                errorMap.put(map_Field, map_Message);
            }

            throw new CustomValidException("필수 항목을 확인해 주세요.", errorMap);
        }
    }
}
