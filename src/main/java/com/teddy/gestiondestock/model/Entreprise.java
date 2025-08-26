package com.teddy.gestiondestock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "entreprise") // ✅ Nom de table en minuscules pour MySQL
public class Entreprise extends AbstractEntity {

    @Column(name = "nom", length = 100) // ✅ Ajout de length pour les champs String
    private String nom;

    @Column(name = "description", columnDefinition = "TEXT") // ✅ Meilleur type pour les descriptions longues
    private String description;

    @Column(name = "code_fiscal", length = 50, unique = true) // ✅ snake_case et unique
    private String codeFiscal;

    @Column(name = "email", length = 100, unique = true) // ✅ Contrainte d'unicité
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "site_web", length = 150) // ✅ snake_case
    private String siteWeb;

    @Column(name = "code", length = 50, unique = true) // ✅ Contrainte d'unicité
    private String code;
}