package com.teddy.gestiondestock.handlers;

import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .code("ENTITY_NOT_FOUND") // Code approprié pour une entité non trouvée
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = ErrorDto.builder()
                .code("INVALID_ENTITY") // Code différent pour une entité invalide
                .httpCode(badRequest.value()) // Correction: badRequest en minuscules
                .message(exception.getMessage())
                .errors(exception.getErrors()) // Supposant que InvalidEntityException a getErrors()
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }
}