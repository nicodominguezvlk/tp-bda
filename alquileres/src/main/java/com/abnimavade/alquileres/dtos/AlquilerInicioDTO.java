package com.abnimavade.alquileres.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor


public class AlquilerInicioDTO {

    private String idCliente;
    private int idEstacionRetiro;
    private LocalDateTime fechaHoraRetiro;
    private Long idTarifa;
}
