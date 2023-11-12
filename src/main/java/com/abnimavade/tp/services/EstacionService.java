package com.abnimavade.tp.services;

import com.abnimavade.tp.dtos.EstacionDTO;


import java.util.List;

public interface EstacionService extends Service<EstacionDTO, Long> {
    List<EstacionDTO> getAll();

    EstacionDTO add(EstacionDTO estacionDto);

    EstacionDTO delete(Long id);

    EstacionDTO update(EstacionDTO estacionDTO);
}
