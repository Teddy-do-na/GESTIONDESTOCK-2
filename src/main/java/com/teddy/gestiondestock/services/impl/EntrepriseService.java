package com.teddy.gestiondestock.services.impl;

import java.util.List;

import com.teddy.gestiondestock.dto.EntrepriseDto;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findByCode(String code);

    /*******  4aee52f8-75b3-4b5b-8725-6dd9d0c4328e  *******/
    List<EntrepriseDto> findAll();

    void delete(Integer id);

    EntrepriseDto findById(Integer id);

}
