package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {
    private Integer id;
    private String code;
    private Instant dateVente;
    private String commentaire;
    private List<LigneVenteDto> ligneVentes;

    public static VentesDto fromEntity(Ventes vente) {
        if (vente == null) {
            return null;
        }
        return VentesDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .commentaire(vente.getCommentaire())
                .build();
    }

    public static Ventes toEntity(VentesDto dto) {
        if (dto == null) {
            return null;
        }
        Ventes vente = new Ventes();
        vente.setId(dto.getId());
        vente.setCode(dto.getCode());
        vente.setDateVente(dto.getDateVente());
        vente.setCommentaire(dto.getCommentaire());
        return vente;
    }
}