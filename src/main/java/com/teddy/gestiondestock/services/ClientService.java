package com.teddy.gestiondestock.services;

import java.util.List;

import com.teddy.gestiondestock.dto.ClientDto;

public interface ClientService {
    ClientDto save(ClientDto dto);
    ClientDto findById(Integer id);
    ClientDto findByEmail(String email);
    List<ClientDto> findAll();
    void delete(Integer id);
}