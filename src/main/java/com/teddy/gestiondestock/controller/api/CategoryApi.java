package com.teddy.gestiondestock.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.teddy.gestiondestock.dto.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Catégorie", description = "API de gestion des catégories")
public interface CategoryApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Enregistrer une catégorie (Ajouter/Modifier)",
        description = "Cette méthode permet d'enregistrer ou de modifier une catégorie",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "La catégorie a été créée ou modifiée",
                content = @Content(schema = @Schema(implementation = CategoryDto.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "La catégorie n'est pas valide",
                content = @Content
            )
        }
    )
    ResponseEntity<CategoryDto> save(@RequestBody CategoryDto dto);


    @GetMapping(value = "/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher une catégorie par ID",
        description = "Cette méthode permet de rechercher une catégorie par son ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Catégorie trouvée",
                content = @Content(schema = @Schema(implementation = CategoryDto.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "L'ID de la catégorie est invalide",
                content = @Content
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Aucune catégorie n'a été trouvée avec l'ID fourni",
                content = @Content
            )
        }
    )
    ResponseEntity<CategoryDto> findById(
        @Parameter(description = "ID de la catégorie à rechercher", required = true)
        @PathVariable("idCategory") Integer idCategory
    );


    @GetMapping(value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher une catégorie par son code",
        description = "Cette méthode permet de rechercher une catégorie par son code",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La catégorie a été trouvée dans la BDD",
                content = @Content(schema = @Schema(implementation = CategoryDto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Aucune catégorie n'existe dans la BDD avec le code fourni",
                content = @Content
            )
        }
    )
    // [CAT, CAT2   , CAT3]
    ResponseEntity<CategoryDto> findByCode(
        @Parameter(description = "Code de la catégorie à rechercher", required = true)
        @PathVariable("code") String code
        
    );


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Renvoyer la liste des catégories",
        description = "Cette méthode permet de renvoyer la liste de toutes les catégories existantes",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La liste des catégories / Une liste vide",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))
                )
            )
        }
    )
    ResponseEntity<List<CategoryDto>> findAll();


    @DeleteMapping(value = "/delete/{idCategory}")
    @Operation(
        summary = "Supprimer une catégorie",
        description = "Cette méthode permet de supprimer une catégorie par son ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "La catégorie a été supprimée"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Catégorie non trouvée"
            )
        }
    )
    ResponseEntity<Void> delete(
        @Parameter(description = "ID de la catégorie à supprimer", required = true)
        @PathVariable("idCategory") Integer idCategory
    );
}