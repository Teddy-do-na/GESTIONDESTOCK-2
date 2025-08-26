package com.teddy.gestiondestock.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ligne_commande_fournisseur") // ✅ Nom de table en minuscules
public class LigneCommandeFournisseur extends AbstractEntity {

    @Column(name = "code", length = 50, unique = true) // ✅ String avec contraintes
    private String code;

    @Column(name = "date_creation") // ✅ Nom de colonne en snake_case
    private Instant dateCreation;
}