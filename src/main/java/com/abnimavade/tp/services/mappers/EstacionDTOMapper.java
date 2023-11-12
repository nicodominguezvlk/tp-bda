package com.abnimavade.tp.services.mappers;

import com.abnimavade.tp.dtos.EstacionDTO;
import com.abnimavade.tp.models.Estacion;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EstacionDTOMapper implements Function<Estacion, EstacionDTO> {

    public EstacionDTO apply(Estacion estacion){
        return new EstacionDTO(estacion.getEstacionId(),
                estacion.getNombre(),
                estacion.getFechaHoraCreacion(),
                estacion.getLatitud(),
                estacion.getLongitud());
    }
}
