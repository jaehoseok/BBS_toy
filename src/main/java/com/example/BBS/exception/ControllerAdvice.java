package com.example.BBS.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionDto> fail(IllegalStateException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage()));
    }

}
