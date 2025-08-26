package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ClientDto {
    private Integer id;
    private String nom;
    private String prenom;
    private AdresseDto adresse;
    private String photo;
    private String mail;
    private String numTel;
    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getName())
                .prenom(client.getPrenom())
                .adresse(AdresseDto.fromEntity(client.getAdresse()))
                .photo(client.getPhoto())
                .mail(client.getEmail())
                .numTel(client.getPhoneNumber())
                .build();
    }

    public static Client toEntity(ClientDto dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
        client.setPhoto(dto.getPhoto());
        client.setEmail(dto.getMail());
        client.setPhoneNumber(dto.getNumTel());
        return client;
    }
}