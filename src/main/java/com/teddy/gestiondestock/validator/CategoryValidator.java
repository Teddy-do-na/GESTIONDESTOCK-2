package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (categoryDto == null) {
            errors.add("Veuillez renseigner les données de la catégorie");
            return errors;
        }

        // Validation du code
        if (!StringUtils.hasLength(categoryDto.getCode())) {
            errors.add("Veuillez renseigner le code de la catégorie");
        }

        // Validation de la désignation
        if (!StringUtils.hasLength(categoryDto.getDesignation())) {
            errors.add("Veuillez renseigner la désignation de la catégorie");
        } else if (categoryDto.getDesignation().length() < 3) {
            errors.add("La désignation doit contenir au moins 3 caractères");
        }

        // Validation optionnelle des articles
        if (categoryDto.getArticles() != null && !categoryDto.getArticles().isEmpty()) {
            categoryDto.getArticles().forEach(article -> {
                List<String> articleErrors = ArticleValidator.validate(article);
                if (!articleErrors.isEmpty()) {
                    errors.addAll(articleErrors);
                }
            });
        }

        return errors;
    }
}