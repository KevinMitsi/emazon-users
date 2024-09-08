package com.kevin.emazon_users.infraestructure.exception;

public class InvalidDatePattern extends RuntimeException{
    public InvalidDatePattern(String message) {
        super(message);
    }
}
