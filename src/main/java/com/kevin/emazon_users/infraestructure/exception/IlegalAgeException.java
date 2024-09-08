package com.kevin.emazon_users.infraestructure.exception;

public class IlegalAgeException extends RuntimeException{
    public IlegalAgeException(String message) {
        super(message);
    }
}
