package com.pm.securitybackend.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> HandlesMethodArgumentNotValidExceptionResponseEntity(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> HandlingEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        log.warn("The email was already Exists");
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UserNotFoundedException.class)
    public ResponseEntity<Map<String,String>> HandlingUserNotFoundedException(UserNotFoundedException ex){
        log.warn("The user not founded in the repository {}",ex.getMessage());
        return ResponseEntity.badRequest().body(Map.of("message",ex.getMessage()));
    }
}
