package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.MvtSTK;
import java.time.Instant;
import java.math.BigDecimal;

public class MvtSTKDto {
    private Integer id;
    private Instant dateMvt;
    private BigDecimal quantite;
    private ArticleDto article;

    // Constructeurs
    public MvtSTKDto() {
    }

    public MvtSTKDto(Integer id, Instant dateMvt, BigDecimal quantite, ArticleDto article) {
        this.id = id;
        this.dateMvt = dateMvt;
        this.quantite = quantite;
        this.article = article;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Instant dateMvt) {
        this.dateMvt = dateMvt;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public ArticleDto getArticle() {
        return article;
    }

    public void setArticle(ArticleDto article) {
        this.article = article;
    }

    // Méthodes de conversion
    public static MvtSTKDto fromEntity(MvtSTK mvtSTK) {
        if (mvtSTK == null) {
            return null;
        }
        
        MvtSTKDto dto = new MvtSTKDto();
        dto.setId(mvtSTK.getId());
        dto.setDateMvt(mvtSTK.getDateMvt());
        dto.setQuantite(mvtSTK.getQuantite());
        dto.setArticle(ArticleDto.fromEntity(mvtSTK.getArticle()));
        
        return dto;
    }

    public static MvtSTK toEntity(MvtSTKDto dto) {
        if (dto == null) {
            return null;
        }
        
        MvtSTK mvtSTK = new MvtSTK();
        mvtSTK.setId(dto.getId());
        mvtSTK.setDateMvt(dto.getDateMvt());
        mvtSTK.setQuantite(dto.getQuantite());
        mvtSTK.setArticle(ArticleDto.toEntity(dto.getArticle()));
        
        return mvtSTK;
    }
}