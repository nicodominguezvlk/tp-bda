package com.abnimavade.alquileres.dtos;

import com.abnimavade.alquileres.models.Tarifa;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private double monto;
    private Long idTarifa;
}
