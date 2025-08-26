// src/main/java/com/teddy/gestiondestock/services/impl/RolesServiceImpl.java

package com.teddy.gestiondestock.services.impl;

import com.teddy.gestiondestock.dto.RolesDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.model.Roles;
import com.teddy.gestiondestock.repository.RolesRepository;
import com.teddy.gestiondestock.services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public RolesDto save(RolesDto dto) {
        if (dto == null) {
            return null;
        }
        Roles roles = RolesDto.toEntity(dto);
        return RolesDto.fromEntity(rolesRepository.save(roles));
    }

    @Override
    public RolesDto findById(Integer id) {
        if (id == null) {
            return null;
        }
        Optional<Roles> roles = rolesRepository.findById(id);
        if (roles.isEmpty()) {
            throw new EntityNotFoundException(
                "Aucun rôle trouvé avec l'ID " + id,
                ErrorCodes.ROLE_NOT_FOUND
            );
        }
        return RolesDto.fromEntity(roles.get());
    }

    @Override
    public RolesDto findByRoleName(String roleName) {
        if (roleName == null || roleName.trim().isEmpty()) {
            return null;
        }
        Optional<Roles> roles = rolesRepository.findByRoleName(roleName.trim());
        if (roles.isEmpty()) {
            throw new EntityNotFoundException(
                "Aucun rôle trouvé avec le nom : " + roleName,
                ErrorCodes.ROLE_NOT_FOUND
            );
        }
        return RolesDto.fromEntity(roles.get());
    }

    @Override
    public List<RolesDto> findAll() {
        return rolesRepository.findAll().stream()
                .map(RolesDto::fromEntity)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }
        rolesRepository.deleteById(id);
    }
}