package com.teddy.gestiondestock.handlers;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;  // Renommé en camelCase pour suivre les conventions Java
    private String code;      // Changé de ErrorCodes à String pour plus de flexibilité
    private String message;
    @Builder.Default         // Pour que Builder utilise cette valeur par défaut
    private List<String> errors = new ArrayList<>();
}