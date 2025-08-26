package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.EntrepriseDto;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


// src/main/java/com/teddy/gestiondestock/validator/EntrepriseValidator.java
@Component
public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("L'entreprise ne peut pas être null");
            return errors;
        }

        if (!StringUtils.hasText(dto.getNom())) {
            errors.add("Le nom est obligatoire");
        }
        if (!StringUtils.hasText(dto.getEmail())) {
            errors.add("L'email est obligatoire");
        } else if (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            errors.add("Format d'email invalide");
        }
        if (!StringUtils.hasText(dto.getCode())) {
            errors.add("Le code est obligatoire");
        }
        if (!StringUtils.hasText(dto.getTelephone())) {
            errors.add("Le téléphone est obligatoire");
        }

        return errors;
    }
}