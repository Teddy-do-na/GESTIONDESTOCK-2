package com.teddy.gestiondestock.services;
import java.util.List;

import com.teddy.gestiondestock.dto.FournisseurDto;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto dto);
    FournisseurDto findById(Integer id);
    FournisseurDto findByEmail(String email);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}