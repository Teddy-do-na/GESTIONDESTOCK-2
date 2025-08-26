package com.teddy.gestiondestock.services;

public interface ArticleService {
    // Define methods for ArticleService herenterface
    ArticloDto save(ArticloDto dto);

    ArticloDto findById(Integer id);

    ArticloDto findByCodeArticle(String codeArticle);

    List<ArticloDto> findAll();

    void delete(Integer id);
}