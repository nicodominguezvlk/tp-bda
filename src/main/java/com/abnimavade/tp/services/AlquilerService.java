package com.abnimavade.tp.services;

import com.abnimavade.tp.dtos.AlquilerDTO;


import java.util.List;

public interface AlquilerService extends Service<AlquilerDTO,Long> {

    List<AlquilerDTO> getAll();

    AlquilerDTO add(AlquilerDTO alquilerDto);

    AlquilerDTO delete(Long id);

    AlquilerDTO update(AlquilerDTO alquilerDTO);
}
