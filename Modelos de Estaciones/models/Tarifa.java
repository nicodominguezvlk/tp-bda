package com.abnimavade.tp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TARIFAS")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long tarifaId;

    @Column(name = "TIPO_TARIFA")
    private long tipoTarifa;

    @Column(name = "DEFINICION")
    private String definicion;

    @Column(name = "DIA_SEMANA")
    private long diaSemana;

    @Column(name = "DIA_MES")
    private long diaMes;

    @Column(name = "MES")
    private long mes;

    @Column(name = "ANIO")
    private long anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private double montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private double montoMinutoFraccion;

    @Column(name = "MONTO_KM")
    private double montoKm;

    @Column(name = "MONTO_HORA")
    private double montoHora;


}
