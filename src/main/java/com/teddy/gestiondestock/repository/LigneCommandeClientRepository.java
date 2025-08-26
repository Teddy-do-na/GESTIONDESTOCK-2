package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
    List<LigneCommandeClient> findAllByCommandeClientId(Integer idCommande);
    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);
}