package com.abnimavade.alquileres.dtos;

import com.abnimavade.alquileres.models.Tarifa;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AlquilerDTO {

    private long alquilerId;
    private String idCliente;
    private long estadoTarifa;
    private int idEstacionRetiro;
    private int idEstacionDevolucion;
    private Date fechaHoraRetiro;
    private Date fechaHoraDevolucion;
    private double monto;
    private Tarifa idTarifa;
}
