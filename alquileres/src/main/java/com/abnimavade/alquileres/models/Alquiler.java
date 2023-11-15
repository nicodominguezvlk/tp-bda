package com.abnimavade.alquileres.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.*;
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
    private long estadoTarifa; // ES ESTADO ALQUILER

    @Column(name = "ESTACION_RETIRO")
    private int idEstacionRetiro;

    @Column(name = "ESTACION_DEVOLUCION")
    private int idEstacionDevolucion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDateTime fechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    // Esto es un OneToOne porque un alquiler tiene una sola tarifa
    @OneToOne
    @JoinColumn(name = "ID_TARIFA", referencedColumnName = "ID")
    private Tarifa tarifa;

    public void setAlquilerId(long alquilerId) {
        this.alquilerId = alquilerId;
    }
}
