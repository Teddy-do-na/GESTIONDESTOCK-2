package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {
    private Integer id;
    private String codeArticle;
    private String designation;
    private BigDecimal prixUnitaireHt;
    private BigDecimal tauxTva;
    private BigDecimal prixUnitaireTtc;
    private String photo;
    private CategoryDto category;

    public static ArticleDto fromEntity(Article article) {
        if (article == null) {
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }

    public static Article toEntity(ArticleDto dto) {
        if (dto == null) {
            return null;
        }
        Article article = new Article();
        article.setId(dto.getId());
        article.setCodeArticle(dto.getCodeArticle());
        article.setDesignation(dto.getDesignation());
        article.setPrixUnitaireHt(dto.getPrixUnitaireHt());
        article.setTauxTva(dto.getTauxTva());
        article.setPrixUnitaireTtc(dto.getPrixUnitaireTtc());
        article.setPhoto(dto.getPhoto());
        article.setCategory(CategoryDto.toEntity(dto.getCategory()));
        return article;
    }
}