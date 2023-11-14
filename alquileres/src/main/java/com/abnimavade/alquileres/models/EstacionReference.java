package com.abnimavade.alquileres.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EstacionReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long estacionId;

    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCreacion;

    private double latitud;

    private double longitud;

}
