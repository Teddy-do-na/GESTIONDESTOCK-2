package com.teddy.gestiondestock.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PexelsPhoto {
    private int id;
    private String url;
    private PexelsPhoto src;

    public PexelsPhoto() {
    }

    public String getMedium() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMedium'");
    }
}