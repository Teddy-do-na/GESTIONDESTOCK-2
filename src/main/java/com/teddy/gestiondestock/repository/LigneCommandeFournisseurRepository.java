package com.teddy.gestiondestock.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy.gestiondestock.model.LigneCommandeFournisseur;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {
    
    // ✅ Changé de Integer à String pour correspondre à l'entité
    List<LigneCommandeFournisseur> findAllByCode(String code);
    
    // ✅ Nom de champ exact (dateCreation) et type Instant
    List<LigneCommandeFournisseur> findAllByDateCreation(Instant dateCreation);
}