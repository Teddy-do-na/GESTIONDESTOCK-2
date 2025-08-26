package com.teddy.gestiondestock.services;

import java.util.List;

import com.teddy.gestiondestock.dto.RolesDto;

public interface RolesService {
    RolesDto save(RolesDto dto);
    RolesDto findById(Integer id);
    RolesDto findByRoleName(String roleName);
    List<RolesDto> findAll();
    void delete(Integer id);
}