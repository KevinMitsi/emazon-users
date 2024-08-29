package com.kevin.emazon_users.infraestructure.controller.exceptionhandler;


import com.kevin.emazon_users.application.dto.ExceptionResponseDto;
import com.kevin.emazon_users.infraestructure.exception.AlreadyCreatedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(AlreadyCreatedUserException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingCategoryException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("AlreadyCreatedUserException", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> inCaseThrowingConstraintViolationException(MethodArgumentNotValidException e){
        return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(e.getFieldErrors().
                stream().
                map(ex -> new ExceptionResponseDto("Error en la creación del campo: "+ex.getField() ,ex.getDefaultMessage(), HttpStatus.BAD_REQUEST)).toList());
    }
}
