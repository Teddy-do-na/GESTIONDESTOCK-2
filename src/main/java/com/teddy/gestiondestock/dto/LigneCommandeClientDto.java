package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.LigneCommandeClient;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {
    private Integer id;
    private ArticleDto article;
    private CommandeClientDto commandeClient;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligne) {
        if (ligne == null) {
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(ligne.getId())
                .article(ArticleDto.fromEntity(ligne.getArticle()))
                .quantite(ligne.getQuantite())
                .prixUnitaire(ligne.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto dto) {
        if (dto == null) {
            return null;
        }
        LigneCommandeClient ligne = new LigneCommandeClient();
        ligne.setId(dto.getId());
        ligne.setArticle(ArticleDto.toEntity(dto.getArticle()));
        ligne.setQuantite(dto.getQuantite());
        ligne.setPrixUnitaire(dto.getPrixUnitaire());
        return ligne;
    }
}