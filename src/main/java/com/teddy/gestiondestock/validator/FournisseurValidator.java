package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null) {
            errors.add("Veuillez renseigner les informations du fournisseur");
            return errors;
        }

        // Validation du nom
        if (!StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("Veuillez renseigner le nom du fournisseur");
        }

        // Validation du prénom
        if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prénom du fournisseur");
        }

        // Validation de l'email
        if (!StringUtils.hasLength(fournisseurDto.getMail())) {
            errors.add("Veuillez renseigner l'email du fournisseur");
        } else if (!isValidEmail(fournisseurDto.getMail())) {
            errors.add("L'email du fournisseur n'est pas valide");
        }

        // Validation du téléphone
        if (!StringUtils.hasLength(fournisseurDto.getNumTel())) {
            errors.add("Veuillez renseigner le numéro de téléphone du fournisseur");
        } else if (!isValidPhoneNumber(fournisseurDto.getNumTel())) {
            errors.add("Le numéro de téléphone du fournisseur n'est pas valide");
        }

        // Validation de l'adresse
        if (fournisseurDto.getAdresse() == null) {
            errors.add("Veuillez renseigner l'adresse du fournisseur");
        } else {
            List<String> adresseErrors = AdresseValidator.validate(fournisseurDto.getAdresse());
            if (!adresseErrors.isEmpty()) {
                errors.addAll(adresseErrors);
            }
        }

        return errors;
    }

    private static boolean isValidEmail(String email) {
        // Expression régulière simple pour validation d'email
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        // Validation simple de numéro de téléphone (accepte +, espaces et chiffres)
        return phoneNumber.matches("^[+0-9\\s]+$") && phoneNumber.length() >= 8;
    }
}