package com.teddy.gestiondestock.exception;


public class EntityNotFoundException extends RuntimeException {
    private final ErrorCodes errorCode;

   

   

    public EntityNotFoundException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public ErrorCodes getErrorCode() {
        return errorCode;
    }
}