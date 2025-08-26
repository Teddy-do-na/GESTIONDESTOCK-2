package com.teddy.gestiondestock.controller;

import com.teddy.gestiondestock.controller.api.ClientApi;
import com.teddy.gestiondestock.dto.ClientDto;
import com.teddy.gestiondestock.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientDto> save(ClientDto dto) {
        ClientDto savedClient = clientService.save(dto);
        return ResponseEntity.ok(savedClient);
    }

    @Override
    public ResponseEntity<ClientDto> findById(Integer id) {
        ClientDto client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @Override
    public ResponseEntity<ClientDto> findByEmail(String email) {
        ClientDto client = clientService.findByEmail(email);
        return ResponseEntity.ok(client);
    }

    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}