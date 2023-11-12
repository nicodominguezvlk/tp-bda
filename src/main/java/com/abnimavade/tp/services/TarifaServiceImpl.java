package com.abnimavade.tp.services;


import com.abnimavade.tp.dtos.TarifaDTO;
import com.abnimavade.tp.models.Tarifa;
import com.abnimavade.tp.repositories.TarifaRepository;
import com.abnimavade.tp.services.mappers.TarifaDTOMapper;
import com.abnimavade.tp.services.mappers.TarifaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TarifaServiceImpl implements TarifaService{

    private final TarifaRepository tarifaRepository;
    private final TarifaDTOMapper DTOmapper;
    private final TarifaMapper entitymapper;

    public TarifaServiceImpl(TarifaRepository tarifaRepository, TarifaDTOMapper DTOmapper, TarifaMapper entitymapper)
    {
        this.tarifaRepository = tarifaRepository;
        this.DTOmapper = DTOmapper;
        this.entitymapper = entitymapper;
    }

    public TarifaDTO add(TarifaDTO entity){
        Optional<Tarifa> tarifa = Stream.of(entity).map(entitymapper).findFirst();
        tarifa.ifPresent(tarifaRepository::save);
        return tarifa.map(DTOmapper).orElseThrow();
    }


    public TarifaDTO update(TarifaDTO entity) {
        Optional<Tarifa> tarifa = Stream.of(entity).map(entitymapper).findFirst();
        tarifa.ifPresent(tarifaRepository::save);
        return tarifa.map(DTOmapper).orElseThrow();

    }

    public TarifaDTO delete(Long id){
        TarifaDTO tarifa = this.getById(id);
        if(tarifa != null)
        {
            Optional<Tarifa> entity = Stream.of(tarifa).map(entitymapper).findFirst();
            entity.ifPresent(tarifaRepository::delete);
        }
        return tarifa;
    }

    private TarifaDTO getById(Long id) {
        Optional<Tarifa> tarifa = this.tarifaRepository.findById(id);
        return tarifa.map(DTOmapper).orElseThrow();
    }

    public List<TarifaDTO> getAll(){
        List<Tarifa> tarifas = this.tarifaRepository.findAll();
        return tarifas.stream().map(DTOmapper).toList();
    }

    public boolean tarifasExists(Long tarifasId){
        return this.tarifaRepository.existsById(tarifasId);
    }
}
