// src/main/java/com/teddy/gestiondestock/dto/CommandeClientDto.java

package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.CommandeClient;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;
    private String code;
    private Instant dateCommande;
    private ClientDto client;
    private List<LigneCommandeClientDto> ligneCommandeClients;

    // Conversion ENTITÉ → DTO
    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .ligneCommandeClients(commandeClient.getLigneCommandeClient() != null ?
                        commandeClient.getLigneCommandeClient().stream()
                                .map(LigneCommandeClientDto::fromEntity)
                                .toList() : null)
                .build();
    }

    // Conversion DTO → ENTITÉ
    public static CommandeClient toEntity(CommandeClientDto dto) {
        if (dto == null) {
            return null;
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(dto.getId());
        commandeClient.setCode(dto.getCode());
        commandeClient.setDateCommande(dto.getDateCommande());
        commandeClient.setClient(ClientDto.toEntity(dto.getClient()));

        // Attention : les lignes de commande ne sont pas automatiquement gérées en save
        // Il faut souvent un service dédié ou une cascade en base
        return commandeClient;
    }
}