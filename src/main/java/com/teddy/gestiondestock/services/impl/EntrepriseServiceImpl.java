package com.teddy.gestiondestock.services.impl;

import com.teddy.gestiondestock.dto.EntrepriseDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.Entreprise;
import com.teddy.gestiondestock.repository.EntrepriseRepository;
import com.teddy.gestiondestock.validator.EntrepriseValidator;
import com.teddy.gestiondestock.services.EntrepriseService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;
    private final EntrepriseValidator entrepriseValidator;

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        // Validation du DTO
        List<String> errors = entrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException(
                "L'entreprise n'est pas valide",
                ErrorCodes.ENTREPRISE_NOT_VALID,
                errors
            );
        }

        // Vérification unicité email
        if (StringUtils.hasText(dto.getEmail())) {
            entrepriseRepository.findByEmail(dto.getEmail())
                .ifPresent(existing -> {
                    if (dto.getId() == null || !existing.getId().equals(dto.getId())) {
                        throw new InvalidEntityException(
                            "Un autre entreprise utilise déjà cet email",
                            ErrorCodes.ENTREPRISE_NOT_FOUND
                        );
                    }
                });
        }

        // Vérification unicité code
        if (StringUtils.hasText(dto.getCode())) {
            entrepriseRepository.findByCode(dto.getCode())
                .ifPresent(existing -> {
                    if (dto.getId() == null || !existing.getId().equals(dto.getId())) {
                        throw new InvalidEntityException(
                            "Un autre entreprise utilise déjà ce code",
                            ErrorCodes.ENTREPRISE_NOT_FOUND
                        );
                    }
                });
        }

        // Conversion DTO → Entité → Sauvegarde → Entité → DTO
        Entreprise savedEntreprise = entrepriseRepository.save(EntrepriseDto.toEntity(dto));
        return EntrepriseDto.fromEntity(savedEntreprise);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de l'entreprise est null");
        }
        return entrepriseRepository.findById(id)
            .map(EntrepriseDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "Aucune entreprise avec l'ID = " + id + " n'a été trouvée",
                ErrorCodes.ENTREPRISE_NOT_FOUND
            ));
    }

    @Override
    public EntrepriseDto findByCode(String code) {
        if (!StringUtils.hasText(code)) {
            throw new IllegalArgumentException("Code de l'entreprise est null ou vide");
        }
        return entrepriseRepository.findByCode(code)
            .map(EntrepriseDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "Aucune entreprise avec le code = " + code + " n'a été trouvée",
                ErrorCodes.ENTREPRISE_NOT_FOUND
            ));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
            .map(EntrepriseDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de l'entreprise est null");
        }
        if (!entrepriseRepository.existsById(id)) {
            throw new EntityNotFoundException(
                "Impossible de supprimer : entreprise avec ID " + id + " non trouvée",
                ErrorCodes.ENTREPRISE_NOT_FOUND
            );
        }
        entrepriseRepository.deleteById(id);
    }
}