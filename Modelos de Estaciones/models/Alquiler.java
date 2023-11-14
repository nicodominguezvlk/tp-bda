package com.abnimavade.tp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "ALQUILERES")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long alquilerId;

    @Column(name = "ID_CLIENTE")
    private String idCliente;

    @Column(name = "ESTADO")
    private long estadoTarifa;

    // VALE:
    // Esto es un OneToMany porque una estacion puede tener muchos alquileres
    @ManyToOne
    @JoinColumn(name = "ESTACION_RETIRO")
    private Estacion estacionRetiro;

    // Lo mismo que arriba
    @ManyToOne
    @JoinColumn(name = "ESTACION_DEVOLUCION")
    private Estacion estacionDevolucion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_RETIRO")
    private Date fechaHoraRetiro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_DEVOLUCION")
    private Date fechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    // Esto es un OneToOne porque un alquiler tiene una sola tarifa
    @OneToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa idTarifa;
}
