package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCode(String code);
}