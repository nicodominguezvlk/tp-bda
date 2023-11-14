package com.abnimavade.tp.dtos;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TarifaDTO {
    private long tarifaId;
    private long tipoTarifa;
    private String definicion;
    private long diaSemana;
    private long diaMes;
    private long mes;
    private long anio;
    private double montoFijoAlquiler;
    private double montoMinutoFraccion;
    private double montoKm;
    private double montoHora;
}
