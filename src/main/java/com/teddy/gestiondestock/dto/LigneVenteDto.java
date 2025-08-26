package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {
    private Integer id;
    private VentesDto vente;
    private BigDecimal quantite;

    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        if (ligneVente == null) {
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .vente(VentesDto.fromEntity(ligneVente.getVente()))
                .quantite(ligneVente.getQuantite())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto dto) {
        if (dto == null) {
            return null;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(dto.getId());
        ligneVente.setVente(VentesDto.toEntity(dto.getVente()));
        ligneVente.setQuantite(dto.getQuantite());
        return ligneVente;
    }
}