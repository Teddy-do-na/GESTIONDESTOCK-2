// src/main/java/com/teddy/gestiondestock/service/EntrepriseService.java

package com.teddy.gestiondestock.services;

import java.util.List;

import com.teddy.gestiondestock.dto.EntrepriseDto;

public interface EntrepriseService {

    /**
     * Enregistre une nouvelle entreprise ou met à jour une entreprise existante.
     *
     * @param dto le DTO de l'entreprise à sauvegarder
     * @return le DTO de l'entreprise sauvegardée
     */
    EntrepriseDto save(EntrepriseDto dto);

    /**
     * Récupère une entreprise par son ID.
     *
     * @param id l'ID de l'entreprise
     * @return le DTO de l'entreprise trouvée
     */
    EntrepriseDto findById(Integer id);

    /**
     * Récupère une entreprise par son code unique.
     *
     * @param code le code de l'entreprise
     * @return le DTO de l'entreprise trouvée
     */
    EntrepriseDto findByCode(String code);

    /**
     * Récupère la liste de toutes les entreprises.
     *
     * @return une liste de DTO d'entreprises
     */
    List<EntrepriseDto> findAll();

    /**
     * Supprime une entreprise par son ID.
     *
     * @param id l'ID de l'entreprise à supprimer
     */
    void delete(Integer id);
}