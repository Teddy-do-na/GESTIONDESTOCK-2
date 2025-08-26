// Chemin : src/main/java/com/teddy/gestiondestock/services/ArticleService.java

package com.teddy.gestiondestock.services;

import java.util.List;

import com.teddy.gestiondestock.dto.ArticleDto;

/**
 * Interface de service pour la gestion des articles.
 * Définit les opérations métier disponibles sur les articles.
 */
public interface ArticleService {

    /**
     * Sauvegarde un article (création ou mise à jour).
     *
     * @param articleDto le DTO de l'article à sauvegarder
     * @return le DTO de l'article sauvegardé
     */
    ArticleDto save(ArticleDto articleDto);

    /**
     * Recherche un article par son identifiant.
     *
     * @param id l'ID de l'article
     * @return le DTO de l'article trouvé
     * @throws EntityNotFoundException si aucun article n'est trouvé
     */
    ArticleDto findById(Integer id);

    /**
     * Recherche un article par son code.
     *
     * @param codeArticle le code de l'article (ex: "ART-001")
     * @return le DTO de l'article trouvé
     * @throws EntityNotFoundException si aucun article n'est trouvé
     */
    ArticleDto findByCodeArticle(String codeArticle);

    /**
     * Récupère la liste de tous les articles.
     *
     * @return une liste de DTO d'articles
     */
    List<ArticleDto> findAll();

    /**
     * Supprime un article par son identifiant.
     *
     * @param id l'ID de l'article à supprimer
     */
    void delete(Integer id);
}