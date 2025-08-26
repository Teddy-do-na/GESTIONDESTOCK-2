package com.teddy.gestiondestock.services;

import java.util.List;

import com.teddy.gestiondestock.dto.CommandeClientDto;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
}