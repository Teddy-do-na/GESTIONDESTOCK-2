package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.LigneCommandeClientDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto) {
        List<String> errors = new ArrayList<>();

        if (ligneCommandeClientDto == null) {
            errors.add("Veuillez renseigner les informations de la ligne de commande client");
            return errors;
        }

        // Validation de l'article
        if (ligneCommandeClientDto.getArticle() == null) {
            errors.add("Veuillez selectionner un article pour la ligne de commande");
        } else {
            List<String> articleErrors = ArticleValidator.validate(ligneCommandeClientDto.getArticle());
            if (!articleErrors.isEmpty()) {
                errors.addAll(articleErrors);
            }
        }

        // Validation de la quantité
        if (ligneCommandeClientDto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantité");
        } else if (ligneCommandeClientDto.getQuantite().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("La quantité doit être supérieure à zéro");
        }

        // Validation du prix unitaire
        if (ligneCommandeClientDto.getPrixUnitaire() == null) {
            errors.add("Veuillez renseigner le prix unitaire");
        } else if (ligneCommandeClientDto.getPrixUnitaire().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Le prix unitaire doit être supérieur à zéro");
        }

        return errors;
    }
}