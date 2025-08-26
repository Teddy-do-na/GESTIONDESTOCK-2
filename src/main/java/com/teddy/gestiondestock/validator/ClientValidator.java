package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.ClientDto;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ClientValidator {

    // Patterns de validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+\\d{1,3}[- ]?)?\\d{10}$");

    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if (clientDto == null) {
            errors.add("Veuillez renseigner les informations du client");
            return errors;
        }

        // Validation du nom
        if (!StringUtils.hasLength(clientDto.getNom())) {
            errors.add("Le champ 'Nom' est obligatoire");
        } else if (clientDto.getNom().length() > 50) {
            errors.add("Le champ 'Nom' ne doit pas dépasser 50 caractères");
        }

        // Validation du prénom
        if (!StringUtils.hasLength(clientDto.getPrenom())) {
            errors.add("Le champ 'Prénom' est obligatoire");
        } else if (clientDto.getPrenom().length() > 50) {
            errors.add("Le champ 'Prénom' ne doit pas dépasser 50 caractères");
        }

        // Validation de l'adresse
        if (clientDto.getAdresse() == null) {
            errors.add("L'adresse du client est obligatoire");
        } else {
            errors.addAll(AdresseValidator.validate(clientDto.getAdresse()));
        }

        // Validation de l'email
        if (!StringUtils.hasLength(clientDto.getMail())) {
            errors.add("L'email du client est obligatoire");
        } else if (!EMAIL_PATTERN.matcher(clientDto.getMail()).matches()) {
            errors.add("L'email n'est pas valide");
        } else if (clientDto.getMail().length() > 100) {
            errors.add("L'email ne doit pas dépasser 100 caractères");
        }

        // Validation du numéro de téléphone
        if (!StringUtils.hasLength(clientDto.getNumTel())) {
            errors.add("Le numéro de téléphone est obligatoire");
        } else if (!PHONE_PATTERN.matcher(clientDto.getNumTel()).matches()) {
            errors.add("Le numéro de téléphone n'est pas valide");
        }

        // Validation optionnelle de la photo
        if (StringUtils.hasLength(clientDto.getPhoto()) && clientDto.getPhoto().length() > 255) {
            errors.add("Le chemin de la photo ne doit pas dépasser 255 caractères");
        }

        return errors;
    }
}