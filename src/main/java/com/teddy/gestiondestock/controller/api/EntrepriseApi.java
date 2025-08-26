// Fichier : src/main/java/com/teddy/gestiondestock/api/EntrepriseApi.java

package com.teddy.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teddy.gestiondestock.dto.EntrepriseDto;
import com.teddy.gestiondestock.services.EntrepriseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/entreprises")
@Tag(name = "Entreprise", description = "Gestion des entreprises")
public class EntrepriseApi {

    private final EntrepriseService entrepriseService;

    public EntrepriseApi(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @PostMapping
    @Operation(summary = "Créer ou mettre à jour une entreprise")
    @ApiResponse(responseCode = "200", description = "Entreprise sauvegardée", content = @Content(schema = @Schema(implementation = EntrepriseDto.class)))
    public ResponseEntity<EntrepriseDto> save(@RequestBody @Parameter(description = "Données de l'entreprise", required = true) EntrepriseDto dto) {
        EntrepriseDto savedDto = entrepriseService.save(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher une entreprise par ID")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = EntrepriseDto.class)))
    public ResponseEntity<EntrepriseDto> findById(@PathVariable @Parameter(description = "ID de l'entreprise") Integer id) {
        EntrepriseDto dto = entrepriseService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Rechercher une entreprise par code")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = EntrepriseDto.class)))
    public ResponseEntity<EntrepriseDto> findByCode(@PathVariable @Parameter(description = "Code de l'entreprise") String code) {
        EntrepriseDto dto = entrepriseService.findByCode(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Lister toutes les entreprises")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = EntrepriseDto.class)))
    public ResponseEntity<List<EntrepriseDto>> findAll() {
        List<EntrepriseDto> dtos = entrepriseService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une entreprise par ID")
    @ApiResponse(responseCode = "204", description = "Supprimée avec succès")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID de l'entreprise à supprimer") Integer id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}