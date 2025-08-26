package com.teddy.gestiondestock.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teddy.gestiondestock.dto.FournisseurDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.Fournisseur;
import com.teddy.gestiondestock.repository.FournisseurRepository;
import com.teddy.gestiondestock.services.FournisseurService;
import com.teddy.gestiondestock.validator.FournisseurValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        Fournisseur savedFournisseur = fournisseurRepository.save(FournisseurDto.toEntity(dto));
        return FournisseurDto.fromEntity(savedFournisseur);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID du fournisseur est null");
        }
        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = " + id + " n'a été trouvé",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public FournisseurDto findByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("Email du fournisseur est null ou vide");
        }
        return fournisseurRepository.findByEmail(email)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun fournisseur avec l'email = " + email + " n'a été trouvé",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID du fournisseur est null");
        }
        fournisseurRepository.deleteById(id);
    }
}