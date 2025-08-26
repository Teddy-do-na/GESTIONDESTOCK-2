// Fichier : src/main/java/com/teddy/gestiondestock/api/UtilisateurApi.java

package com.teddy.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teddy.gestiondestock.dto.UtilisateurDto;
import com.teddy.gestiondestock.services.UtilisateurService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, 
             allowedHeaders = "*", 
             allowCredentials = "true",
             methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@Tag(name = "Utilisateur", description = "Gestion des utilisateurs du système")
public class UtilisateurApi {

    private final UtilisateurService utilisateurService;

    public UtilisateurApi(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    @Operation(summary = "Créer ou mettre à jour un utilisateur")
    @ApiResponse(responseCode = "200", description = "Utilisateur sauvegardé", content = @Content(schema = @Schema(implementation = UtilisateurDto.class)))
    public ResponseEntity<UtilisateurDto> save(@RequestBody @Parameter(description = "Données de l'utilisateur", required = true) UtilisateurDto dto) {
        UtilisateurDto savedDto = utilisateurService.save(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Rechercher un utilisateur par ID")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UtilisateurDto.class)))
    public ResponseEntity<UtilisateurDto> findById(@PathVariable @Parameter(description = "ID de l'utilisateur") Integer id) {
        UtilisateurDto dto = utilisateurService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Rechercher un utilisateur par email")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UtilisateurDto.class)))
    public ResponseEntity<UtilisateurDto> findByEmail(@PathVariable @Parameter(description = "Email de l'utilisateur") String email) {
        UtilisateurDto dto = utilisateurService.findByEmail(email);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @Operation(summary = "Lister tous les utilisateurs")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UtilisateurDto.class)))
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        List<UtilisateurDto> dtos = utilisateurService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur par ID")
    @ApiResponse(responseCode = "204", description = "Utilisateur supprimé")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID de l'utilisateur à supprimer") Integer id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}