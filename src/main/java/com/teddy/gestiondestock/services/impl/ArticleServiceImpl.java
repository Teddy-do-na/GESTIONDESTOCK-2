package com.teddy.gestiondestock.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.teddy.gestiondestock.dto.ArticleDto;
import com.teddy.gestiondestock.services.ArticleService;

@Service // ⬅️ Très important : cette annotation rend la classe "injectable"
public class ArticleServiceImpl implements ArticleService {

    @Override
    public ArticleDto save(ArticleDto dto) {
        // TODO: Implémente la logique d'enregistrement
        throw new UnsupportedOperationException("Non implémenté");
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
        throw new UnsupportedOperationException("Non implémenté");
    }

    @Override
    public void delete(Integer id) {
        // TODO: Implémente la logique de suppression
        throw new UnsupportedOperationException("Non implémenté");
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        // TODO: Peut-être un doublon de findByCode ? À clarifier selon ton besoin
        throw new UnsupportedOperationException("Non implémenté");
    }
}