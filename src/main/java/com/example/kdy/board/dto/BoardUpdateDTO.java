package com.example.kdy.board.dto;

import com.example.kdy.board.dto.validation.BoardValidationDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateDTO extends BoardValidationDTO {
    private Long bSeq;

    private String bRegId;
    private String updateDate;

    private String bFileName;
    private String bFilePath;
}
