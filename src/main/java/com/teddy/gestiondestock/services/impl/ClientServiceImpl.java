package com.teddy.gestiondestock.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teddy.gestiondestock.dto.ClientDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.exception.InvalidEntityException;
import com.teddy.gestiondestock.model.Client;
import com.teddy.gestiondestock.repository.ClientRepository;
import com.teddy.gestiondestock.services.ClientService;
import com.teddy.gestiondestock.validator.ClientValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        Client savedClient = clientRepository.save(ClientDto.toEntity(dto));
        return ClientDto.fromEntity(savedClient);
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID du client est null");
        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec l'ID = " + id + " n'a été trouvé",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public ClientDto findByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("Email du client est null ou vide");
        }
        return clientRepository.findByEmail(email)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec l'email = " + email + " n'a été trouvé",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID du client est null");
        }
        clientRepository.deleteById(id);
    }
}