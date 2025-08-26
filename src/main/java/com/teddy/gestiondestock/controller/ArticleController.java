// Chemin : src/main/java/com/teddy/gestiondestock/controller/ArticleController.java
package com.teddy.gestiondestock.controller;

import com.teddy.gestiondestock.controller.api.ArticleApi;
import com.teddy.gestiondestock.dto.ArticleDto;
import com.teddy.gestiondestock.services.ArticleService; // ← Interface, pas impl

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class ArticleController implements ArticleApi {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ArticleDto> save(ArticleDto dto) {
        ArticleDto savedArticle = articleService.save(dto);
        return ResponseEntity.ok(savedArticle);
    }

    @Override
    public ResponseEntity<ArticleDto> findById(Integer id) {
        ArticleDto article = articleService.findById(id);
        return ResponseEntity.ok(article);
    }

    @Override
    public ResponseEntity<ArticleDto> findByCodeArticle(String codeArticle) {
        ArticleDto article = articleService.findByCodeArticle(codeArticle);
        return ResponseEntity.ok(article);
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAll() {
        List<ArticleDto> articles = articleService.findAll();
        return ResponseEntity.ok(articles);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}