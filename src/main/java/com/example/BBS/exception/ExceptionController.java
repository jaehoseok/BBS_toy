package com.example.BBS.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionResponse> error(IllegalStateException e){
        return ResponseEntity.badRequest().body(new ExceptionResponse(e.getMessage()));
    }
}
