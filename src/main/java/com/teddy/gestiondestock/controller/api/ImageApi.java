// Fichier : src/main/java/com/teddy/gestiondestock/api/ImageApi.java

package com.teddy.gestiondestock.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teddy.gestiondestock.services.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Image", description = "Recherche d'images via Pexels")
public class ImageApi {

    private final ImageService imageService;

    public ImageApi(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/api/images/search")
    @Operation(
        summary = "Rechercher une image pertinente",
        description = "Recherche une image sur Pexels selon un mot-clé et retourne une URL d'image",
        responses = @ApiResponse(
            responseCode = "200",
            description = "URL de l'image trouvée ou image par défaut",
            content = @Content(schema = @Schema(type = "string", example = "https://images.pexels.com/photos/..."))
        )
    )
    public ResponseEntity<String> searchImage(
        @RequestParam("query")
        @Parameter(description = "Mot-clé de recherche (ex: chaise, bureau, etc.)", required = true)
        String query) {

        String imageUrl = imageService.getRelevantImageUrl(query);
        return ResponseEntity.ok(imageUrl);
    }
}