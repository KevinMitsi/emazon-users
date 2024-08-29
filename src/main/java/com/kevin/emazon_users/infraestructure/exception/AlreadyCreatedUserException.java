package com.kevin.emazon_users.infraestructure.exception;

public class AlreadyCreatedUserException extends RuntimeException{
    public AlreadyCreatedUserException(String message) {
        super(message);
    }
}
