// src/main/java/com/teddy/gestiondestock/controller/api/RolesApi.java

package com.teddy.gestiondestock.controller.api;

import com.teddy.gestiondestock.dto.RolesDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.teddy.gestiondestock.Utils.Constants.APP_ROOT;

@Tag(name = "Rôles", description = "API de gestion des rôles utilisateurs")
@RequestMapping(value = APP_ROOT + "/roles")
public interface RolesApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Enregistrer un rôle",
        description = "Crée ou met à jour un rôle utilisateur",
        tags = { "Rôles" }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Rôle créé", content = @Content(schema = @Schema(implementation = RolesDto.class))),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    ResponseEntity<RolesDto> save(@RequestBody RolesDto dto);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher un rôle par ID",
        description = "Retourne un rôle par son identifiant",
        tags = { "Rôles" }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rôle trouvé", content = @Content(schema = @Schema(implementation = RolesDto.class))),
        @ApiResponse(responseCode = "404", description = "Rôle non trouvé")
    })
    ResponseEntity<RolesDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/utilisateur/{utilisateurId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher un rôle par utilisateur",
        description = "Retourne le rôle associé à un utilisateur par son ID",
        tags = { "Rôles" }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rôle trouvé", content = @Content(schema = @Schema(implementation = RolesDto.class))),
        @ApiResponse(responseCode = "404", description = "Aucun rôle trouvé pour cet utilisateur")
    })
    ResponseEntity<RolesDto> findByUtilisateurId(@PathVariable("utilisateurId") Integer utilisateurId);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Lister tous les rôles",
        description = "Retourne la liste de tous les rôles",
        tags = { "Rôles" }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des rôles", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RolesDto.class))))
    })
    ResponseEntity<List<RolesDto>> findAll();

    @DeleteMapping(value = "/{id}")
    @Operation(
        summary = "Supprimer un rôle",
        description = "Supprime un rôle par son identifiant",
        tags = { "Rôles" }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Rôle supprimé"),
        @ApiResponse(responseCode = "404", description = "Rôle non trouvé")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}