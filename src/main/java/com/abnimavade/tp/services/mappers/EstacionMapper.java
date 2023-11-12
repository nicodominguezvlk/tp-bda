package com.abnimavade.tp.services.mappers;

import com.abnimavade.tp.dtos.EstacionDTO;
import com.abnimavade.tp.models.Estacion;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EstacionMapper implements Function<EstacionDTO, Estacion> {

    public Estacion apply(EstacionDTO estacionDTO)
    {
        return new Estacion(estacionDTO.getEstacionId(),
                estacionDTO.getNombre(),
                estacionDTO.getFechaHoraCreacion(),
                estacionDTO.getLatitud(),
                estacionDTO.getLongitud());
    }
}
