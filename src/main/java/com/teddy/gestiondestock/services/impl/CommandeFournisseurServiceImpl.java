package com.teddy.gestiondestock.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teddy.gestiondestock.dto.CommandeFournisseurDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.CommandeFournisseur;
import com.teddy.gestiondestock.repository.CommandeFournisseurRepository;
import com.teddy.gestiondestock.services.CommandeFournisseurService;
import com.teddy.gestiondestock.validator.CommandeFournisseurValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeFournisseurRepository;

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        CommandeFournisseur entityToSave = CommandeFournisseurDto.toEntity(dto);
        CommandeFournisseur savedEntity = commandeFournisseurRepository.save(entityToSave);
        return CommandeFournisseurDto.fromEntity(savedEntity);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la commande fournisseur est null");
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur avec l'ID = " + id + " n'a été trouvée",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasText(code)) {
            throw new IllegalArgumentException("Le code de la commande fournisseur est null ou vide");
        }
        return commandeFournisseurRepository.findByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur avec le code = " + code + " n'a été trouvée",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la commande fournisseur est null");
        }
        commandeFournisseurRepository.deleteById(id);
    }
}