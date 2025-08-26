package com.teddy.gestiondestock.repository;

import com.teddy.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByPhoneNumber(String phoneNumber);
}