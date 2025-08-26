package com.teddy.gestiondestock.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teddy.gestiondestock.dto.UtilisateurDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.Utilisateur;
import com.teddy.gestiondestock.repository.UtilisateurRepository;
import com.teddy.gestiondestock.services.UtilisateurService;
import com.teddy.gestiondestock.validator.UtilisateurValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        Utilisateur savedUtilisateur = utilisateurRepository.save(UtilisateurDto.toEntity(dto));
        return UtilisateurDto.fromEntity(savedUtilisateur);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de l'utilisateur est null");
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = " + id + " n'a été trouvé",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("Email de l'utilisateur est null ou vide");
        }
        return utilisateurRepository.findByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email + " n'a été trouvé",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de l'utilisateur est null");
        }
        utilisateurRepository.deleteById(id);
    }
}