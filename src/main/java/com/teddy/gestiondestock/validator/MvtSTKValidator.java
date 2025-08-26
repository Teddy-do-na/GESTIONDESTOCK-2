package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.MvtSTKDto;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MvtSTKValidator {

    public static List<String> validate(MvtSTKDto mvtSTKDto) {
        List<String> errors = new ArrayList<>();

        if (mvtSTKDto == null) {
            errors.add("Veuillez renseigner les données du mouvement de stock");
            return errors;
        }

        // Validation de la date
        if (mvtSTKDto.getDateMvt() == null) {
            errors.add("Veuillez renseigner la date du mouvement de stock");
        } else if (mvtSTKDto.getDateMvt().isAfter(Instant.now())) {
            errors.add("La date du mouvement ne peut pas être dans le futur");
        }

        // Validation de la quantité
        if (mvtSTKDto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantité du mouvement de stock");
        } else if (mvtSTKDto.getQuantite().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("La quantité doit être supérieure à zéro");
        }

        // Validation de l'article
        if (mvtSTKDto.getArticle() == null) {
            errors.add("Veuillez renseigner l'article associé au mouvement de stock");
        } else {
            if (mvtSTKDto.getArticle().getId() == null) {
                errors.add("Veuillez renseigner l'ID de l'article");
            }
            if (!StringUtils.hasLength(mvtSTKDto.getArticle().getCodeArticle())) {
                errors.add("Veuillez renseigner le code de l'article");
            }
        }

        return errors;
    }
}