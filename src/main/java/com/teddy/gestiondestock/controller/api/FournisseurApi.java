// Fichier : src/main/java/com/teddy/gestiondestock/api/FournisseurApi.java

package com.teddy.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teddy.gestiondestock.dto.FournisseurDto;
import com.teddy.gestiondestock.services.FournisseurService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/fournisseurs")
@Tag(name = "Fournisseur", description = "API de gestion des fournisseurs")
public class FournisseurApi {

    private final FournisseurService fournisseurService;

    public FournisseurApi(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @PostMapping
    @Operation(summary = "Créer ou mettre à jour un fournisseur")
    @ApiResponse(responseCode = "200", description = "Fournisseur sauvegardé", content = @Content(schema = @Schema(implementation = FournisseurDto.class)))
    public ResponseEntity<FournisseurDto> save(@RequestBody @Parameter(description = "Données du fournisseur", required = true) FournisseurDto dto) {
        FournisseurDto savedDto = fournisseurService.save(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un fournisseur par ID")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FournisseurDto.class)))
    public ResponseEntity<FournisseurDto> findById(@PathVariable @Parameter(description = "ID du fournisseur") Integer id) {
        FournisseurDto dto = fournisseurService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Rechercher un fournisseur par email")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FournisseurDto.class)))
    public ResponseEntity<FournisseurDto> findByEmail(@PathVariable @Parameter(description = "Email du fournisseur") String email) {
        FournisseurDto dto = fournisseurService.findByEmail(email);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Lister tous les fournisseurs")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FournisseurDto.class)))
    public ResponseEntity<List<FournisseurDto>> findAll() {
        List<FournisseurDto> dtos = fournisseurService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un fournisseur par ID")
    @ApiResponse(responseCode = "204", description = "Supprimé avec succès")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID du fournisseur à supprimer") Integer id) {
        fournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}