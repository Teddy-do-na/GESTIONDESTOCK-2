package com.teddy.gestiondestock.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "Fournisseur")
public class Fournisseur extends AbstractEntity {
    @Column(name = "nom")
    private String nom;
    @Embedded
    private Adresse adresse;
    @Column(name = "prenom")
    private String prenom;

    @Column(name= "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "fournisseur")

    private List<CommandeFournisseur> commandesFournisseurs;

    // Additional fields and methods can be added as needed


}