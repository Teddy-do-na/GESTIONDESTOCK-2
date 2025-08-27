package com.teddy.gestiondestock.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.teddy.gestiondestock.dto.UtilisateurDto;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {
            errors.add("Veuillez renseigner les données de l'utilisateur");
            return errors;
        }

        // Validation du nom
        if (!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }

        // Validation du prénom
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prénom de l'utilisateur");
        }

        // Validation de l'email
        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner l'email de l'utilisateur");
        } else if (!isValidEmail(utilisateurDto.getEmail())) {
            errors.add("L'email n'est pas valide");
        }

        // Validation de la date de naissance
        if (utilisateurDto.getDateDeNaissance() == null) {
            errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
        } else {
            LocalDate dateNaissance = utilisateurDto.getDateDeNaissance();
            LocalDate now = LocalDate.now();
            if (dateNaissance.isAfter(now.minusYears(18))) {
                errors.add("L'utilisateur doit être majeur");
            }
        }

        // Validation du mot de passe
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        } else if (utilisateurDto.getMotDePasse().length() < 8) {
            errors.add("Le mot de passe doit contenir au moins 8 caractères");
        }

        // Validation de l'adresse
        if (utilisateurDto.getAdresse() == null) {
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
        } else {
            errors.addAll(AdresseValidator.validate(utilisateurDto.getAdresse()));
        }

        // La photo est optionnelle donc pas de validation

        // Validation de l'entreprise (si nécessaire)
        

        return errors;
    }

    private static boolean isValidEmail(String email) {
        // Expression régulière simple pour valider un email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}