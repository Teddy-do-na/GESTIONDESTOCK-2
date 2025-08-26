package com.teddy.gestiondestock.controller.api;

import com.teddy.gestiondestock.dto.ClientDto;
import com.teddy.gestiondestock.Utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client", description = "API de gestion des clients")
@RequestMapping(Constants.APP_ROOT + "/clients")
public interface ClientApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Enregistrer un client (Ajouter/Modifier)",
        description = "Cette méthode permet d'enregistrer ou de modifier un client",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Le client a été créé ou modifié",
                content = @Content(schema = @Schema(implementation = ClientDto.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Le client n'est pas valide",
                content = @Content
            )
        }
    )
    ResponseEntity<ClientDto> save(@RequestBody ClientDto dto);


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher un client par ID",
        description = "Cette méthode permet de rechercher un client par son ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Client trouvé",
                content = @Content(schema = @Schema(implementation = ClientDto.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "L'ID du client est invalide",
                content = @Content
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Aucun client n'a été trouvé avec l'ID fourni",
                content = @Content
            )
        }
    )
    ResponseEntity<ClientDto> findById(
        @Parameter(description = "ID du client à rechercher", required = true)
        @PathVariable("id") Integer id
    );


    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher un client par email",
        description = "Cette méthode permet de rechercher un client par son adresse email",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Client trouvé",
                content = @Content(schema = @Schema(implementation = ClientDto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Aucun client n'existe avec cet email",
                content = @Content
            )
        }
    )
    ResponseEntity<ClientDto> findByEmail(
        @Parameter(description = "Email du client à rechercher", required = true)
        @PathVariable("email") String email
    );


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Renvoyer la liste des clients",
        description = "Cette méthode permet de renvoyer la liste de tous les clients existants",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La liste des clients / Une liste vide",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))
                )
            )
        }
    )
    ResponseEntity<List<ClientDto>> findAll();


    @DeleteMapping(value = "/delete/{id}")
    @Operation(
        summary = "Supprimer un client",
        description = "Cette méthode permet de supprimer un client par son ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Le client a été supprimé"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Client non trouvé"
            )
        }
    )
    ResponseEntity<Void> delete(
        @Parameter(description = "ID du client à supprimer", required = true)
        @PathVariable("id") Integer id
    );
}