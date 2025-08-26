package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
    Optional<CommandeFournisseur> findByCode(String code);
    List<CommandeFournisseur> findAllByFournisseurId(Integer idFournisseur);
}