package com.teddy.gestiondestock.services;

import com.teddy.gestiondestock.model.PexelsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ImageService {

    @Value("${pexels.api-key}")
    private String apiKey;

    @Autowired
    private RestTemplate template;

    public String getRelevantImageUrl(String query) {
        try {
            // Encoder le mot-clé (ex: "chaise de bureau")
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

            String url = "https://api.pexels.com/v1/search?query=" + encodedQuery + "&per_page=1";

            // Définir l'en-tête d'autorisation
            var headers = new org.springframework.http.HttpHeaders();
            headers.set("Authorization", apiKey);

            var entity = new org.springframework.http.HttpEntity<>((String) null, headers);

            // Appel à l'API Pexels
            var response = template.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                PexelsResponse.class
            );

            // Extraire l'URL de l'image
            if (response.getBody() != null && !response.getBody().getPhotos().isEmpty()) {
                return response.getBody().getPhotos().get(0).getSrc().getMedium();
            }
        } catch (Exception e) {
            System.err.println("Erreur appel Pexels : " + e.getMessage());
        }

        // Image par défaut si erreur
        return "https://via.placeholder.com/400x300?text=No+Image+Found";
    }
}