package com.abnimavade.tp.services.mappers;

import com.abnimavade.tp.dtos.AlquilerDTO;
import com.abnimavade.tp.models.Alquiler;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlquilerMapper implements Function<AlquilerDTO, Alquiler> {

    public Alquiler apply(AlquilerDTO alquilerDTO)
    {
        return new Alquiler(alquilerDTO.getAlquilerId(),
                alquilerDTO.getIdCliente(),
                alquilerDTO.getEstadoTarifa(),
                alquilerDTO.getEstacionRetiro(),
                alquilerDTO.getEstacionDevolucion(),
                alquilerDTO.getFechaHoraRetiro(),
                alquilerDTO.getFechaHoraDevolucion(),
                alquilerDTO.getMonto(),
                alquilerDTO.getIdTarifa());
    }
}
