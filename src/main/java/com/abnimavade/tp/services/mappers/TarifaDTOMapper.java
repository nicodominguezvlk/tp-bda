package com.abnimavade.tp.services.mappers;

import com.abnimavade.tp.dtos.TarifaDTO;
import com.abnimavade.tp.models.Tarifa;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TarifaDTOMapper implements Function<Tarifa, TarifaDTO> {
    public TarifaDTO apply(Tarifa tarifa)
    {
        return new TarifaDTO(tarifa.getTarifaId(),
                tarifa.getTipoTarifa(),
                tarifa.getDefinicion(),
                tarifa.getDiaSemana(),
                tarifa.getDiaMes(),
                tarifa.getMes(),
                tarifa.getAnio(),
                tarifa.getMontoFijoAlquiler(),
                tarifa.getMontoMinutoFraccion(),
                tarifa.getMontoKm(),
                tarifa.getMontoHora());
    }
}
