package com.kevin.emazon_users.infraestructure.controller.exceptionhandler;


import com.kevin.emazon_users.application.dto.ExceptionResponseDto;
import com.kevin.emazon_users.infraestructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    public static final String ALREADY_CREATED_USER_EXCEPTION_MESSAGE = "AlreadyCreatedUserException";
    public static final String ERROR_CREATION_KEY = "Error en la creación del campo: ";
    public static final String INVALID_DATE_PATTERN_EXCEPTION_MESSAGE = "InvalidDatePatternException";
    public static final String ILEGAL_AGE_EXCEPTION_MESSAGE = "IlegalAgeException";
    public static final String NOT_FOUND_ROLE_EXCEPTION_MESSAGE = "NotFoundRoleException";
    public static final String NOT_FOUND_USER_EXCEPTION_MESSSAGE = "NotFoundUserException";

    @ExceptionHandler(AlreadyCreatedUserException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingAlreadyCreatedUserException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(ALREADY_CREATED_USER_EXCEPTION_MESSAGE, e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> inCaseThrowingConstraintViolationException(MethodArgumentNotValidException e){
        return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(e.getFieldErrors().
                stream().
                map(ex -> new ExceptionResponseDto(ERROR_CREATION_KEY +ex.getField() ,ex.getDefaultMessage(), HttpStatus.BAD_REQUEST)).toList());
    }

    @ExceptionHandler(InvalidDatePattern.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingInvalidDatePattern(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(INVALID_DATE_PATTERN_EXCEPTION_MESSAGE, e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(IlegalAgeException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingIlegalAge(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(ILEGAL_AGE_EXCEPTION_MESSAGE, e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotFoundRoleException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingNotFoundRoleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(NOT_FOUND_ROLE_EXCEPTION_MESSAGE, e.getMessage(), HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingNotFounUserException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(NOT_FOUND_USER_EXCEPTION_MESSSAGE, e.getMessage(), HttpStatus.BAD_REQUEST));
    }


}
