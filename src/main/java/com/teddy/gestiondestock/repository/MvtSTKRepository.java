package com.teddy.gestiondestock.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teddy.gestiondestock.model.MvtSTK;

public interface MvtSTKRepository extends JpaRepository<MvtSTK, Integer> {
    // Vous pouvez ajouter ici des méthodes personnalisées si nécessaire
    List<MvtSTK> findAllByArticleId(Integer idArticle);
List<MvtSTK> findByDateMvtBetween(Instant startDate, Instant endDate);
}