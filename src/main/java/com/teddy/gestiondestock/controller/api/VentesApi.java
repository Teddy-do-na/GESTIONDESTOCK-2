// Fichier : src/main/java/com/teddy/gestiondestock/api/VentesApi.java

package com.teddy.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teddy.gestiondestock.dto.VentesDto;
import com.teddy.gestiondestock.services.VentesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ventes")
@Tag(name = "Vente", description = "Gestion des ventes clients")
public class VentesApi {

    private final VentesService ventesService;

    public VentesApi(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @PostMapping
    @Operation(summary = "Créer ou mettre à jour une vente")
    @ApiResponse(
        responseCode = "200",
        description = "Vente enregistrée avec succès",
        content = @Content(schema = @Schema(implementation = VentesDto.class))
    )
    public ResponseEntity<VentesDto> save(
        @RequestBody
        @Parameter(description = "Données de la vente à enregistrer", required = true)
        VentesDto dto) {
        VentesDto savedDto = ventesService.save(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher une vente par ID")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = VentesDto.class)))
    public ResponseEntity<VentesDto> findById(
        @PathVariable
        @Parameter(description = "ID de la vente")
        Integer id) {
        VentesDto dto = ventesService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Rechercher une vente par code (ex: VENTE-2025-001)")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = VentesDto.class)))
    public ResponseEntity<VentesDto> findByCode(
        @PathVariable
        @Parameter(description = "Code unique de la vente")
        String code) {
        VentesDto dto = ventesService.findByCode(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les ventes")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = VentesDto.class)))
    public ResponseEntity<List<VentesDto>> findAll() {
        List<VentesDto> dtos = ventesService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une vente par ID")
    @ApiResponse(responseCode = "204", description = "Vente supprimée")
    public ResponseEntity<Void> delete(
        @PathVariable
        @Parameter(description = "ID de la vente à supprimer")
        Integer id) {
        ventesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}