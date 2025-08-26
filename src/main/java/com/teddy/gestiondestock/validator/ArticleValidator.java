package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if (articleDto == null) {
            errors.add("Veuillez renseigner les données de l'article");
            return errors;
        }

        // Validation du code article
        if (!StringUtils.hasLength(articleDto.getCodeArticle())) {
            errors.add("Le code article est obligatoire");
        } else if (articleDto.getCodeArticle().length() < 3 || articleDto.getCodeArticle().length() > 20) {
            errors.add("Le code article doit contenir entre 3 et 20 caractères");
        }

        // Validation de la désignation
        if (!StringUtils.hasLength(articleDto.getDesignation())) {
            errors.add("La désignation est obligatoire");
        } else if (articleDto.getDesignation().length() < 5) {
            errors.add("La désignation doit contenir au moins 5 caractères");
        }

        // Validation des prix
        if (articleDto.getPrixUnitaireHt() == null) {
            errors.add("Le prix unitaire HT est obligatoire");
        } else if (articleDto.getPrixUnitaireHt().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Le prix unitaire HT doit être supérieur à zéro");
        }

        if (articleDto.getTauxTva() == null) {
            errors.add("Le taux de TVA est obligatoire");
        } else if (articleDto.getTauxTva().compareTo(BigDecimal.ZERO) < 0) {
            errors.add("Le taux de TVA ne peut pas être négatif");
        }

        if (articleDto.getPrixUnitaireTtc() == null) {
            errors.add("Le prix unitaire TTC est obligatoire");
        } else if (articleDto.getPrixUnitaireTtc().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Le prix unitaire TTC doit être supérieur à zéro");
        }

        // Validation de la catégorie
        if (articleDto.getCategory() == null) {
            errors.add("Une catégorie doit être associée à l'article");
        } else if (articleDto.getCategory().getId() == null) {
            errors.add("L'ID de la catégorie est obligatoire");
        }

        // Validation optionnelle de la photo
        if (articleDto.getPhoto() != null && articleDto.getPhoto().length() > 255) {
            errors.add("Le chemin de la photo ne doit pas dépasser 255 caractères");
        }

        return errors;
    }
}