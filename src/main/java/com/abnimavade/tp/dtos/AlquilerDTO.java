package com.abnimavade.tp.dtos;

import com.abnimavade.tp.models.Estacion;
import com.abnimavade.tp.models.Tarifa;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AlquilerDTO {

    private long alquilerId;
    private String idCliente;
    private long estadoTarifa;
    private Estacion estacionRetiro;
    private Estacion estacionDevolucion;
    private Date fechaHoraRetiro;
    private Date fechaHoraDevolucion;
    private double monto;
    private Tarifa idTarifa;
}
