package com.teddy.gestiondestock.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teddy.gestiondestock.dto.ArticleDto;
import com.teddy.gestiondestock.dto.CategoryDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.Article;
import com.teddy.gestiondestock.model.Category;
import com.teddy.gestiondestock.repository.ArticleRepository;
import com.teddy.gestiondestock.services.ArticleService;
import com.teddy.gestiondestock.validator.ArticleValidator;
import com.teddy.gestiondestock.validator.CategoryValidator;

import org.springframework.util.StringUtils;
@Service // ⬅️ Très important : cette annotation rend la classe "injectable"
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        // TODO: Implémente la logique d'enregistrement
       List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("L'article 'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        Article savedArticle = articleRepository.save(ArticleDto.toEntity(dto));
        return ArticleDto.fromEntity(savedArticle);
    }

    @Override
    public ArticleDto findById(Integer id) {
        // TODO: Implémente la logique de recherche par ID
        throw new UnsupportedOperationException("Non implémenté");
    }

    public ArticleDto findByCode(String code) {
        // TODO: Implémente la logique de recherche par code
        throw new UnsupportedOperationException("Non implémenté");
    }

    @Override
    public List<ArticleDto> findAll() {
        // TODO: Implémente la logique de récupération de tous les articles
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        // TODO: Implémente la logique de suppression
        if (id == null) {
            throw new IllegalArgumentException("ID d'article est null");
        }
        articleRepository.deleteById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String code) {
        // TODO: Peut-être un doublon de findByCode ? À clarifier selon ton besoin
         if (!StringUtils.hasText(code)) {
            throw new IllegalArgumentException("Le code de catégorie est null ou vide");
        }
        return articleRepository.findByCodeArticle(code)
                .map(ArticleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec le code = " + code + " n'a été trouvée",
                        ErrorCodes.ARTICLE_NOT_FOUND
                ));
    }
}