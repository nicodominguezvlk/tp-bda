package com.abnimavade.tp.services.mappers;

import com.abnimavade.tp.dtos.AlquilerDTO;
import com.abnimavade.tp.models.Alquiler;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlquilerDTOMapper implements Function<Alquiler, AlquilerDTO> {

    @Override
    public AlquilerDTO apply(Alquiler alquiler){
        return new AlquilerDTO(alquiler.getAlquilerId(),
                alquiler.getIdCliente(),
                alquiler.getEstadoTarifa(),
                alquiler.getEstacionRetiro(),
                alquiler.getEstacionDevolucion(),
                alquiler.getFechaHoraRetiro(),
                alquiler.getFechaHoraDevolucion(),
                alquiler.getMonto(),
                alquiler.getIdTarifa());
    }
}
