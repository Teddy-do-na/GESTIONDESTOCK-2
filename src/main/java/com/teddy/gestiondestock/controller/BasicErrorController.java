package com.teddy.gestiondestock.controller;

import com.teddy.gestiondestock.exception.ErrorCodes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class BasicErrorController implements ErrorController {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorResponse {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private Integer code; // Notre code métier (ex: 2000, 4001, etc.)
    }

    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = status != null ? Integer.valueOf(status.toString()) : 500;

        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        String message = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_MESSAGE))
                .map(Object::toString)
                .orElse(httpStatus.getReasonPhrase());

        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        // Déterminer un code métier (ErrorCodes) selon le type d'erreur
        Integer applicationCode = resolveApplicationErrorCode(statusCode, path);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                statusCode,
                httpStatus.getReasonPhrase(),
                message,
                path,
                applicationCode
        );

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    /**
     * Associe une erreur HTTP ou un chemin à un code métier de ErrorCodes
     */
    private Integer resolveApplicationErrorCode(Integer httpStatus, String path) {
        if (path == null) return null;

        // Exemples : on peut mapper certains chemins ou statuts à des codes métier
        if (path.contains("/articles") && httpStatus == 404) {
            return ErrorCodes.ARTICLE_NOT_FOUND.getCode();
        } else if (path.contains("/articles") && httpStatus == 400) {
            return ErrorCodes.ARTICLE_NOT_VALID.getCode();
        } else if (path.contains("/categories") && httpStatus == 404) {
            return ErrorCodes.CATEGORY_NOT_FOUND.getCode();
        } else if (path.contains("/clients") && httpStatus == 404) {
            return ErrorCodes.CLIENT_NOT_FOUND.getCode();
        } else if (path.contains("/utilisateurs") && httpStatus == 404) {
            return ErrorCodes.UTILISATEUR_NOT_FOUND.getCode();
        } else if (path.contains("/commandes-clients") && httpStatus == 400) {
            return ErrorCodes.COMMANDE_CLIENT_NOT_VALID.getCode();
        }
        // Ajoute d'autres mappages selon tes besoins

        // Par défaut, on peut retourner un code générique
        if (httpStatus == 404) {
            return ErrorCodes.UTILISATEUR_NOT_FOUND.getCode(); // ou un NOT_FOUND générique
        } else if (httpStatus == 400) {
            return ErrorCodes.UTILISATEUR_NOT_VALID.getCode(); // ou un NOT_VALID générique
        }

        return null; // ou tu peux définir un code par défaut
    }

    // Méthode pour compatibilité (obsolète dans Spring Boot 2.3+, mais conservée si besoin)
    public String getErrorPath() {
        return "/error";
    }
}