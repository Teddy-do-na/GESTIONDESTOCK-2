package com.teddy.gestiondestock.services.impl;

import com.teddy.gestiondestock.dto.CategoryDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.Category;
import com.teddy.gestiondestock.repository.CategoryRepository;
import com.teddy.gestiondestock.validator.CategoryValidator;
import com.teddy.gestiondestock.services.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("La catégorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        Category savedCategory = categoryRepository.save(CategoryDto.toEntity(dto));
        return CategoryDto.fromEntity(savedCategory);
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de catégorie est null");
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                 .orElseThrow(() -> new EntityNotFoundException(
                         "Aucune catégorie avec l'ID = " + id + " n'a été trouvée",
                         ErrorCodes.CATEGORY_NOT_FOUND
                 ));
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasText(code)) {
            throw new IllegalArgumentException("Le code de catégorie est null ou vide");
        }
        return categoryRepository.findByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec le code = " + code + " n'a été trouvée",
                        ErrorCodes.CATEGORY_NOT_FOUND
                ));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de catégorie est null");
        }
        categoryRepository.deleteById(id);
    }
}