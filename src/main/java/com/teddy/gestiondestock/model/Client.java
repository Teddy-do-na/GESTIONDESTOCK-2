package com.teddy.gestiondestock.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Client")
public class Client extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "Prenom")
    private String prenom;

    @Column(name = "photo")
    private String photo;

    @Embedded
    private Adresse adresse;
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandesClients;

    // Additional fields and methods can be added as needed
}