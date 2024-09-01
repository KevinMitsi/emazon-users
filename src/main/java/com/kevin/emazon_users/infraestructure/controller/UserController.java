package com.kevin.emazon_users.infraestructure.controller;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @PostMapping("/register")
    @Secured("ROLE_ADMINISTRADOR")
    public ResponseEntity<String> createAdmin(@Valid @RequestBody UserDto userDto){
        userHandler.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Felicidades ha creado el admin: "+ userDto.getName());
    }
    @PostMapping("/register/whWorker")
    @Secured("ROLE_ADMINISTRADOR")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserDto userDto){
        userHandler.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Felicidades ha creado el Auxiliar: "+ userDto.getName());
    }




}
