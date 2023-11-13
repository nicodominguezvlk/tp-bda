package com.abnimavade.tp.dtos;

import lombok.*;

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
