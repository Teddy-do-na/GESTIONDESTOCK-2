package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CategoryDto {
    private Integer id;
    private String code;
    private String designation;
    private List<ArticleDto> articles;

    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .articles(category.getArticles() != null ? 
                    category.getArticles().stream()
                        .map(ArticleDto::fromEntity)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public static Category toEntity(CategoryDto dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(dto.getId());
        category.setCode(dto.getCode());
        category.setDesignation(dto.getDesignation());
        if (dto.getArticles() != null) {
            category.setArticles(dto.getArticles().stream()
                .map(ArticleDto::toEntity)
                .collect(Collectors.toList()));
        }
        return category;
    }
}