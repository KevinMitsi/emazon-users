package com.kevin.emazon_users.infraestructure.exception;

public class AlreadyExpiredJwtException extends RuntimeException{
    public AlreadyExpiredJwtException(String message) {
        super(message);
    }
}
