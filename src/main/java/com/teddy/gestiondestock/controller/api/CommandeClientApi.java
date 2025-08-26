package com.teddy.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teddy.gestiondestock.dto.CommandeClientDto;
import com.teddy.gestiondestock.services.CommandeClientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/commandes-clients")
@Tag(name = "Commande Client", description = "API de gestion des commandes clients")
public class CommandeClientApi {

    private final CommandeClientService commandeClientService;

    public CommandeClientApi(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @PostMapping
    @Operation(summary = "Créer une commande client", description = "Enregistre une nouvelle commande client")
    @ApiResponse(responseCode = "201", description = "Commande client créée", content = @Content(schema = @Schema(implementation = CommandeClientDto.class)))
    public ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto) {
        CommandeClientDto savedDto = commandeClientService.save(dto);
        return ResponseEntity.ok(savedDto); // Tu peux aussi utiliser 201 Created
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher une commande client par ID", description = "Retourne une commande client par son identifiant")
    public ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id) {
        CommandeClientDto dto = commandeClientService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Rechercher une commande client par code", description = "Retourne une commande client par son code")
    public ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code) {
        CommandeClientDto dto = commandeClientService.findByCode(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les commandes clients", description = "Retourne la liste de toutes les commandes clients")
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        List<CommandeClientDto> dtos = commandeClientService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une commande client", description = "Supprime une commande client par son ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}