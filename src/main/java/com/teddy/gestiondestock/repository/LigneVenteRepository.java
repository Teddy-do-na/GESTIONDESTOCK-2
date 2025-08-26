package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
    List<LigneVente> findAllByVenteId(Integer idVente);
    List<LigneVente> findAllByQuantite(Integer quantite);
}