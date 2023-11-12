package com.abnimavade.tp.services.mappers;

import com.abnimavade.tp.dtos.TarifaDTO;
import com.abnimavade.tp.models.Tarifa;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TarifaMapper implements Function<TarifaDTO, Tarifa> {

    public Tarifa apply(TarifaDTO tarifaDTO)
    {
        return new Tarifa(tarifaDTO.getTarifaId(),
                tarifaDTO.getTipoTarifa(),
                tarifaDTO.getDefinicion(),
                tarifaDTO.getDiaSemana(),
                tarifaDTO.getDiaMes(),
                tarifaDTO.getMes(),
                tarifaDTO.getAnio(),
                tarifaDTO.getMontoFijoAlquiler(),
                tarifaDTO.getMontoMinutoFraccion(),
                tarifaDTO.getMontoKm(),
                tarifaDTO.getMontoHora());
    }
}
