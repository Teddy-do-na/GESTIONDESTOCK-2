package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.Entreprise;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

    // Recherche par email
    Optional<Entreprise> findByEmail(String email);

    // Recherche par code
    Optional<Entreprise> findByCode(String code);

    // Recherche par nom
    Optional<Entreprise> findByNom(String nom);

    // Recherche par code fiscal
    Optional<Entreprise> findByCodeFiscal(String codeFiscal);

    // Vérifications d'existence
    boolean existsByCodeFiscal(String codeFiscal);
    boolean existsByEmail(String email);
    boolean existsByCode(String code);
}