package com.teddy.gestiondestock.controller.api;

import com.teddy.gestiondestock.dto.ArticleDto;
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

@Tag(name = "Article", description = "API gestion des articles")
@RequestMapping(Constants.APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Enregistrer un article (Ajouter/Modifier)",
        description = "Cette méthode permet d'enregistrer ou de modifier un article",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "L'article a été créé ou modifié",
                content = @Content(schema = @Schema(implementation = ArticleDto.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "L'article n'est pas valide",
                content = @Content
            )
        }
    )
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto dto);

    @GetMapping(value = "/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher un article par ID",
        description = "Cette méthode permet de rechercher un article par son ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Article trouvé",
                content = @Content(schema = @Schema(implementation = ArticleDto.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "L'ID de l'article est invalide",
                content = @Content
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Aucun article n'a été trouvé avec l'ID fourni",
                content = @Content
            )
        }
    )
    ResponseEntity<ArticleDto> findById(
        @Parameter(description = "ID de l'article à rechercher", required = true)
        @PathVariable("idArticle") Integer idArticle
    );

    @GetMapping(value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Rechercher un article par son code",
        description = "Cette méthode permet de rechercher un article par son code",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "L'article a été trouvé dans la BDD",
                content = @Content(schema = @Schema(implementation = ArticleDto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Aucun article n'existe dans la BDD avec le code fourni",
                content = @Content
            )
        }
    )
    ResponseEntity<ArticleDto> findByCodeArticle(
        @Parameter(description = "Code de l'article à rechercher", required = true)
        @PathVariable("code") String code
    );

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Renvoyer la liste des articles",
        description = "Cette méthode permet de rechercher et de renvoyer la liste des articles qui existent dans la BDD",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "La liste des articles / Une liste vide",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                )
            )
        }
    )
    ResponseEntity<List<ArticleDto>> findAll();

    @DeleteMapping(value = "/delete/{idArticle}")
    @Operation(
        summary = "Supprimer un article",
        description = "Cette méthode permet de supprimer un article par son ID",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "L'article a été supprimé"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Article non trouvé"
            )
        }
    )
    ResponseEntity<Void> delete(
        @Parameter(description = "ID de l'article à supprimer", required = true)
        @PathVariable("idArticle") Integer id
    );
}