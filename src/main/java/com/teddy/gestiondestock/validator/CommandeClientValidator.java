package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("Veuillez renseigner les données de la commande");
            return errors;
        }

        // Validation du code
        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande");
        }

        // Validation de la date
        if (dto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande");
        }

        // Validation du client
        if (dto.getClient() == null || dto.getClient().getId() == null) {
            errors.add("Veuillez renseigner le client");
        }

        // Validation des lignes de commande (optionnel selon les besoins)
        if (dto.getLigneCommandeClients() == null || dto.getLigneCommandeClients().isEmpty()) {
            errors.add("Veuillez renseigner au moins une ligne de commande");
        } else {
            dto.getLigneCommandeClients().forEach(ligne -> {
                if (ligne.getArticle() == null || ligne.getArticle().getId() == null) {
                    errors.add("Veuillez renseigner l'article pour toutes les lignes de commande");
                }
               
            });
        }

        return errors;
    }
}