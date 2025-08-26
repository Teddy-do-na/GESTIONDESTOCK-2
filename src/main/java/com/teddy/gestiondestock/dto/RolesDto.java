package com.teddy.gestiondestock.dto;

import com.teddy.gestiondestock.model.Roles;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {
    private Integer id;
    private String roleName;
    private UtilisateurDto utilisateur;

    public static RolesDto fromEntity(Roles role) {
        if (role == null) {
            return null;
        }
        return RolesDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
        
                .build();
    }

    public static Roles toEntity(RolesDto dto) {
        if (dto == null) {
            return null;
        }
        Roles role = new Roles();
        role.setId(dto.getId());
        role.setRoleName(dto.getRoleName());
      
        return role;
    }
}