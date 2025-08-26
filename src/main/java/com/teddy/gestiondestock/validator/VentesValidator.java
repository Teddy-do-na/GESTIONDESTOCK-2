package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class VentesValidator {

    public static List<String> validate(VentesDto ventesDto) {
        List<String> errors = new ArrayList<>();

        if (ventesDto == null) {
            errors.add("Veuillez renseigner les données de la vente");
            return errors;
        }

        // Validation du code
        if (!StringUtils.hasLength(ventesDto.getCode())) {
            errors.add("Veuillez renseigner le code de la vente");
        }

        // Validation de la date
        if (ventesDto.getDateVente() == null) {
            errors.add("Veuillez renseigner la date de la vente");
        } else if (ventesDto.getDateVente().isAfter(Instant.now())) {
            errors.add("La date de vente ne peut pas être dans le futur");
        }

        // Validation des lignes de vente (optionnelle selon besoins)
        if (ventesDto.getLigneVentes() != null && ventesDto.getLigneVentes().isEmpty()) {
            errors.add("Une vente doit avoir au moins une ligne de vente");
        }

        return errors;
    }
}