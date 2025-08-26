package com.teddy.gestiondestock.services;
import java.util.List;

import com.teddy.gestiondestock.dto.VentesDto;

/**
 * Service pour la gestion des ventes.
 */
public interface VentesService {

    /**
     * Enregistre une nouvelle vente (ou met à jour).
     */
    VentesDto save(VentesDto dto);

    /**
     * Recherche une vente par son identifiant.
     */
    VentesDto findById(Integer id);

    /**
     * Recherche une vente par son code (ex: VENTE-2024-001).
     */
    VentesDto findByCode(String code);

    /**
     * Récupère toutes les ventes.
     */
    List<VentesDto> findAll();

    /**
     * Supprime une vente par ID.
     */
    void delete(Integer id);
}