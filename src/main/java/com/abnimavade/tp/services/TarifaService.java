package com.abnimavade.tp.services;

import com.abnimavade.tp.dtos.TarifaDTO;


import java.util.List;

public interface TarifaService extends Service<TarifaDTO, Long>{
    List<TarifaDTO> getAll();

    TarifaDTO add(TarifaDTO tarifaDto);

    TarifaDTO delete(Long id);

    TarifaDTO update(TarifaDTO tarifaDTO);
}
