package com.kevin.emazon_users.infraestructure.exception;

public class NotFoundRoleException extends RuntimeException{
    public NotFoundRoleException(String message) {
        super(message);
    }
}
