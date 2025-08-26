package com.teddy.gestiondestock.exception;

import java.util.Collections;
import java.util.List;

public class InvalidEntityException extends RuntimeException {
    private final ErrorCodes errorCode;  // Changé de ErrorCodes à String
    private final List<String> errors;

    // Constructeur complet
    public InvalidEntityException(String message, ErrorCodes categoryNotValid, List<String> errors) {
        super(message);
        this.errorCode = categoryNotValid;
        this.errors = errors != null ? errors : Collections.emptyList();
    }

    // Constructeur sans erreurs détaillées
    public InvalidEntityException(String message, ErrorCodes errorCode) {
        this(message, errorCode, Collections.emptyList());
    }

    // Constructeur historique (à garder pour compatibilité)
    public InvalidEntityException(String message, List<String> errors) {
        this(message, "INVALID_ENTITY", errors);
    }

    // Constructeur avec cause
    public InvalidEntityException(String message, ErrorCodes errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errors = Collections.emptyList();
    }

    public InvalidEntityException(String message, String string, List<String> errors2) {
        this.errorCode = null;
        //TODO Auto-generated constructor stub
        this.errors = null;
    }

    // Getters
    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors); // Retourne une liste immuable
    }
}