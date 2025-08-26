package com.teddy.gestiondestock.controller;

import com.teddy.gestiondestock.controller.api.CategoryApi;
import com.teddy.gestiondestock.dto.CategoryDto;
import com.teddy.gestiondestock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<CategoryDto> save(CategoryDto dto) {
        CategoryDto savedCategory = categoryService.save(dto);
        return ResponseEntity.ok(savedCategory);
    }

    @Override
    public ResponseEntity<CategoryDto> findById(Integer idCategory) {
        CategoryDto category = categoryService.findById(idCategory);
        return ResponseEntity.ok(category);
    }

    @Override
    public ResponseEntity<CategoryDto> findByCode(String code) {
        CategoryDto category = categoryService.findByCode(code);
        return ResponseEntity.ok(category);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<Void> delete(Integer idCategory) {
        categoryService.delete(idCategory);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}