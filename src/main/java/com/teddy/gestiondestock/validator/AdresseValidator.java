package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.AdresseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {

    public static List<String> validate(AdresseDto adresseDto) {
        List<String> errors = new ArrayList<>();

        if (adresseDto == null) {
            errors.add("Veuillez renseigner l'adresse");
            return errors;
        }

        // Validation de l'adresse1
        if (!StringUtils.hasLength(adresseDto.getAdresse1())) {
            errors.add("Le champ 'Adresse 1' est obligatoire");
        } else if (adresseDto.getAdresse1().length() > 100) {
            errors.add("Le champ 'Adresse 1' ne doit pas dépasser 100 caractères");
        }

        // Validation optionnelle de l'adresse2
        if (StringUtils.hasLength(adresseDto.getAdresse2()) && adresseDto.getAdresse2().length() > 100) {
            errors.add("Le champ 'Adresse 2' ne doit pas dépasser 100 caractères");
        }

        // Validation du code postal
        if (!StringUtils.hasLength(adresseDto.getCodePostal())) {
            errors.add("Le champ 'Code postal' est obligatoire");
        } else if (!adresseDto.getCodePostal().matches("^[0-9]{5}$")) {
            errors.add("Le code postal doit contenir exactement 5 chiffres");
        }

        // Validation de la ville
        if (!StringUtils.hasLength(adresseDto.getVille())) {
            errors.add("Le champ 'Ville' est obligatoire");
        } else if (adresseDto.getVille().length() > 50) {
            errors.add("Le champ 'Ville' ne doit pas dépasser 50 caractères");
        }

        // Validation du pays
        if (!StringUtils.hasLength(adresseDto.getPays())) {
            errors.add("Le champ 'Pays' est obligatoire");
        } else if (adresseDto.getPays().length() > 50) {
            errors.add("Le champ 'Pays' ne doit pas dépasser 50 caractères");
        }

        return errors;
    }
}