package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {
    Optional<Ventes> findByCode(String code);
}