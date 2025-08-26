// src/main/java/com/teddy/gestiondestock/dto/EntrepriseDto.java
package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.Entreprise;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntrepriseDto {

    private Integer id;
    private String nom;
    private String description;
    private String codeFiscal;
    private String photo;
    private String email;
    private String telephone;  // ✅ Renommé pour correspondre à l'entité
    private String siteWeb;
    private String code;

    /**
     * Convertit une entité Entreprise en EntrepriseDto
     */
    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (entreprise == null) {
            return null;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .codeFiscal(entreprise.getCodeFiscal())// ✅ Supposé que tu ajoutes ce champ dans Entreprise
                .email(entreprise.getEmail())
                .telephone(entreprise.getTelephone()) // ✅ Utilise le bon getter
                .siteWeb(entreprise.getSiteWeb())
                .code(entreprise.getCode())
                .build();
    }

    /**
     * Convertit un EntrepriseDto en entité Entreprise
     */
    public static Entreprise toEntity(EntrepriseDto dto) {
        if (dto == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(dto.getId());
        entreprise.setNom(dto.getNom());
        entreprise.setDescription(dto.getDescription());
        entreprise.setCodeFiscal(dto.getCodeFiscal()); // ✅ Supposé ajouté dans Entreprise
        entreprise.setEmail(dto.getEmail());
        entreprise.setTelephone(dto.getTelephone()); // ✅ Bon nom de champ
        entreprise.setSiteWeb(dto.getSiteWeb());
        entreprise.setCode(dto.getCode());
        return entreprise;
    }
}