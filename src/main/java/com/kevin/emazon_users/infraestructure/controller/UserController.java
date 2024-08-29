package com.kevin.emazon_users.infraestructure.controller;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final IUserHandler userHandler;
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body("Felicidades ha creado el usuario: "+ userDto.getName());
    }
}
