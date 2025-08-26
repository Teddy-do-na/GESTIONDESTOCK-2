package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validate(RolesDto rolesDto) {
        List<String> errors = new ArrayList<>();

        if (rolesDto == null) {
            errors.add("Veuillez renseigner le nom du rôle");
            errors.add("Veuillez associer un utilisateur au rôle");
            return errors;
        }

        if (!StringUtils.hasLength(rolesDto.getRoleName())) {
            errors.add("Veuillez renseigner le nom du rôle");
        }

        if (rolesDto.getUtilisateur() == null || rolesDto.getUtilisateur().getId() == null) {
            errors.add("Veuillez associer un utilisateur au rôle");
        }

        return errors;
    }
}