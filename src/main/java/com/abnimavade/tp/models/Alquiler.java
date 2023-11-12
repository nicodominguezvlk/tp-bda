package com.abnimavade.tp.models;

import jakarta.persistence.*;
import java.util.Date;
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
    private long estadoTarifa;

    @ManyToOne
    @JoinColumn(name = "ESTACION_RETIRO")
    private Estacion estacionRetiro;

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

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa idTarifa;
}
