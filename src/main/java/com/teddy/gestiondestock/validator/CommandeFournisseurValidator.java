package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez renseigner les données de la commande fournisseur");
            return errors;
        }

        // Validation du code
        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande fournisseur");
        }

        // Validation de la date
        if (dto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande fournisseur");
        }

        // Validation du fournisseur
        if (dto.getFournisseur() == null || dto.getFournisseur().getId() == null) {
            errors.add("Veuillez renseigner le fournisseur");
        }

        // Validation des lignes de commande (optionnel selon les besoins)
        if (dto.getLigneCommandeFournisseurs() == null || dto.getLigneCommandeFournisseurs().isEmpty()) {
            errors.add("Veuillez renseigner au moins une ligne de commande fournisseur");
        } 
        

        return errors;
    }
}