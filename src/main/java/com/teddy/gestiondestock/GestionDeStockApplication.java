package com.teddy.gestiondestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = "com.teddy.gestiondestock.model")
@EnableJpaRepositories(basePackages = "com.teddy.gestiondestock.repository")
public class GestionDeStockApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionDeStockApplication.class, args);
    }
}