package com.abnimavade.alquileres.services;

import com.abnimavade.alquileres.models.Tarifa;
import com.abnimavade.alquileres.repositories.TarifaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("tarifaService")
@Transactional
public class TarifaService {


    private final TarifaRepository tarifaRepository;

    @Autowired
    public TarifaService(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    public Tarifa getTarifaById(Long id){
        Optional<Tarifa> tarifa = tarifaRepository.findById(id);
        if(tarifa.isPresent()){
            return tarifa.get();
        }
        throw new RuntimeException("La tarifa con ese ID no existe");

    }
}