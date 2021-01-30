package com.example.BBS.exception;

import lombok.Data;

@Data
public class ExceptionDto {

    String messages;

    public ExceptionDto(String messages) {
        this.messages = messages;
    }
}
