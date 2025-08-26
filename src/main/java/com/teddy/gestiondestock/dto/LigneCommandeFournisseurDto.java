package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.LigneCommandeFournisseur;
import java.time.Instant;

public class LigneCommandeFournisseurDto {
    private Integer id;
    private String code;
    private Instant dateCreation;

    // Constructeurs
    public LigneCommandeFournisseurDto() {
    }

    public LigneCommandeFournisseurDto(Integer id, String code, Instant dateCreation) {
        this.id = id;
        this.code = code;
        this.dateCreation = dateCreation;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Instant dateCreation) {
        this.dateCreation = dateCreation;
    }

    // Méthodes de conversion
    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligne) {
        if (ligne == null) {
            return null;
        }
        
        LigneCommandeFournisseurDto dto = new LigneCommandeFournisseurDto();
        dto.setId(ligne.getId());
        dto.setCode(ligne.getCode());
        dto.setDateCreation(ligne.getDateCreation());
        
        return dto;
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        
        LigneCommandeFournisseur ligne = new LigneCommandeFournisseur();
        ligne.setId(dto.getId());
        ligne.setCode(dto.getCode());
        ligne.setDateCreation(dto.getDateCreation());
        
        return ligne;
    }
}