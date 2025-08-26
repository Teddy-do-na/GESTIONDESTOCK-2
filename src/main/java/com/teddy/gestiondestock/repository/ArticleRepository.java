package com.teddy.gestiondestock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy.gestiondestock.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findByCodeArticle(String codeArticle);
   // List<Article> findAllByCategoryId(Integer idCategory);
}