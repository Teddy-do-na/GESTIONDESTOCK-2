package com.teddy.gestiondestock.validator;

import com.teddy.gestiondestock.dto.LigneVenteDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class LigneVenteValidator {

    public static List<String> validate(LigneVenteDto ligneVenteDto) {
        List<String> errors = new ArrayList<>();

        if (ligneVenteDto == null) {
            errors.add("Veuillez renseigner les informations de la ligne de vente");
            return errors;
        }

        // Validation de la vente
        if (ligneVenteDto.getVente() == null) {
            errors.add("Veuillez associer la ligne de vente à une vente");
        } 
        

        // Validation de la quantité
        if (ligneVenteDto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantité vendue");
        } else {
            if (ligneVenteDto.getQuantite().compareTo(BigDecimal.ZERO) <= 0) {
                errors.add("La quantité vendue doit être supérieure à zéro");
            }
            if (ligneVenteDto.getQuantite().scale() > 2) {
                errors.add("La quantité ne doit pas avoir plus de 2 décimales");
            }
        }

        return errors;
    }
}