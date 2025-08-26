package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    Optional<Fournisseur> findByEmail(String email);
    Optional<Fournisseur> findByTelephone(String telephone);
}