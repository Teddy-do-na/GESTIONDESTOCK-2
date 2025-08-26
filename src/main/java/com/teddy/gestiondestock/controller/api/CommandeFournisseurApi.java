package com.teddy.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teddy.gestiondestock.dto.CommandeFournisseurDto;
import com.teddy.gestiondestock.services.CommandeFournisseurService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/commandes-fournisseurs")
@Tag(name = "Commande Fournisseur", description = "API de gestion des commandes fournisseurs")
public class CommandeFournisseurApi {

    private final CommandeFournisseurService commandeFournisseurService;

    public CommandeFournisseurApi(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @PostMapping
    @Operation(
        summary = "Créer une commande fournisseur",
        description = "Enregistre une nouvelle commande fournisseur dans le système",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objet DTO de la commande fournisseur à créer",
            required = true,
            content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))
        )
    )
    @ApiResponse(
        responseCode = "200",
        description = "Commande fournisseur créée avec succès",
        content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))
    )
    public ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto dto) {
        CommandeFournisseurDto savedDto = commandeFournisseurService.save(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher une commande fournisseur par ID")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class)))
    public ResponseEntity<CommandeFournisseurDto> findById(
        @Parameter(description = "ID de la commande fournisseur", required = true)
        @PathVariable Integer id) {
        CommandeFournisseurDto dto = commandeFournisseurService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Rechercher une commande fournisseur par code")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class)))
    public ResponseEntity<CommandeFournisseurDto> findByCode(
        @Parameter(description = "Code de la commande fournisseur", required = true)
        @PathVariable String code) {
        CommandeFournisseurDto dto = commandeFournisseurService.findByCode(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les commandes fournisseurs")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class)))
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        List<CommandeFournisseurDto> dtos = commandeFournisseurService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une commande fournisseur par ID")
    @ApiResponse(responseCode = "204", description = "Suppression effectuée")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID de la commande à supprimer", required = true)
        @PathVariable Integer id) {
        commandeFournisseurService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}