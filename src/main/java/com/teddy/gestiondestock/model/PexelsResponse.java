package com.teddy.gestiondestock.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PexelsResponse {
    private List<PexelsPhoto> photos = new ArrayList<>();
}