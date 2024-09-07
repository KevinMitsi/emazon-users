package com.kevin.emazon_users.infraestructure.controller;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    public static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRADOR";
    public static final String CREATED_ADMIN_CONFIRM_MESSAGE = "Felicidades ha creado el admin: ";
    public static final String CREATED_WH_CONFIRMATION_MESSAGE = "Felicidades ha creado el Auxiliar: ";


    private final IUserHandler userHandler;
    @PostMapping("/register")
    @Secured(ROLE_ADMINISTRATOR)
    public ResponseEntity<String> createAdmin(@Valid @RequestBody UserDto userDto){
        userHandler.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CREATED_ADMIN_CONFIRM_MESSAGE + userDto.getName());
    }


    @PostMapping("/register/whWorker")
    @Secured(ROLE_ADMINISTRATOR)
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserDto userDto){
        userHandler.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CREATED_WH_CONFIRMATION_MESSAGE + userDto.getName());
    }




}
