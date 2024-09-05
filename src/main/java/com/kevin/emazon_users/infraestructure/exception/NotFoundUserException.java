package com.kevin.emazon_users.infraestructure.exception;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(String message) {
        super(message);
    }
}
