package com.teddy.gestiondestock.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Article")
public class Article extends AbstractEntity {

    @Column(name = "codeArticle")
    private String codeArticle;
    
    @Column(name = "designation")
    private String designation;

    @Column(name = "prixUnitaireHt")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxTva")
    private BigDecimal tauxTva;

    @Column(name = "prixUnitairettc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;


    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;
}