package com.example.campeones.model.entity;

import androidx.room.Embedded;

public class CampeonPosicion {

    @Embedded
    public Campeon campeon;

    @Embedded(prefix="posicion_")
    public Posicion posicion;

}
