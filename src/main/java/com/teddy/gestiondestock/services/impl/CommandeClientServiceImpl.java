// src/main/java/com/teddy/gestiondestock/services/impl/CommandeClientServiceImpl.java

package com.teddy.gestiondestock.services.impl;

import com.teddy.gestiondestock.dto.CommandeClientDto;
import com.teddy.gestiondestock.exception.EntityNotFoundException;
import com.teddy.gestiondestock.exception.ErrorCodes;
import com.teddy.gestiondestock.model.CommandeClient;
import com.teddy.gestiondestock.repository.CommandeClientRepository;
import com.teddy.gestiondestock.services.CommandeClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        CommandeClient entity = CommandeClientDto.toEntity(dto);
        CommandeClient saved = commandeClientRepository.save(entity);
        return CommandeClientDto.fromEntity(saved);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) return null;
        Optional<CommandeClient> commande = commandeClientRepository.findById(id);
        if (commande.isEmpty()) {
            throw new EntityNotFoundException(
                "Aucune commande client trouvée avec l'ID " + id,
                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
            );
        }
        return CommandeClientDto.fromEntity(commande.get());
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (code == null || code.trim().isEmpty()) return null;
        Optional<CommandeClient> commande = commandeClientRepository.findByCode(code);
        if (commande.isEmpty()) {
            throw new EntityNotFoundException(
                "Aucune commande client trouvée avec le code " + code,
                ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
            );
        }
        return CommandeClientDto.fromEntity(commande.get());
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }
        commandeClientRepository.deleteById(id);
    }
}