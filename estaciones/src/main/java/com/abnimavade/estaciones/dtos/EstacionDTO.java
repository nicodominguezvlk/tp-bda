package com.abnimavade.estaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EstacionDTO {
    private long estacionId;
    private String nombre;
    private Date fechaHoraCreacion;
    private double latitud;
    private double longitud;
}
