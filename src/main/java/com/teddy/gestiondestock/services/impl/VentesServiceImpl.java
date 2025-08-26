package com.teddy.gestiondestock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teddy.gestiondestock.dto.VentesDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.Ventes;
import com.teddy.gestiondestock.repository.VentesRepository;
import com.teddy.gestiondestock.services.VentesService;
import com.teddy.gestiondestock.validator.VentesValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentesServiceImpl implements VentesService {

    private final VentesRepository ventesRepository;

    @Override
    public VentesDto save(VentesDto dto) {
        // Validation
        List<String> errors = VentesValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException(
                "La vente n'est pas valide", 
                ErrorCodes.VENTES_NOT_VALID, 
                errors
            );
        }

        // Conversion DTO → Entité
        Ventes savedVente = ventesRepository.save(VentesDto.toEntity(dto));
        
        // Retourne le DTO sauvegardé
        return VentesDto.fromEntity(savedVente);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la vente est null");
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente avec l'ID = " + id + " n'a été trouvée",
                        ErrorCodes.VENTES_NOT_FOUND
                ));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasText(code)) {
            throw new IllegalArgumentException("Le code de la vente est null ou vide");
        }
        return ventesRepository.findByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente avec le code = " + code + " n'a été trouvée",
                        ErrorCodes.VENTES_NOT_FOUND
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la vente est null");
        }
        ventesRepository.deleteById(id);
    }
}