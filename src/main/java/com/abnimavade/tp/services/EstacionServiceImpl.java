package com.abnimavade.tp.services;


import com.abnimavade.tp.dtos.EstacionDTO;
import com.abnimavade.tp.models.Estacion;
import com.abnimavade.tp.repositories.EstacionRepository;
import com.abnimavade.tp.services.mappers.EstacionDTOMapper;
import com.abnimavade.tp.services.mappers.EstacionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EstacionServiceImpl implements EstacionService {

    private final EstacionRepository estacionRepository;
    private final EstacionDTOMapper DTOmapper;
    private final EstacionMapper entitymapper;

    public EstacionServiceImpl(EstacionRepository estacionRepository, EstacionDTOMapper DTOmapper, EstacionMapper entitymapper)
    {
        this.estacionRepository = estacionRepository;
        this.DTOmapper = DTOmapper;
        this.entitymapper = entitymapper;
    }


    public EstacionDTO add(EstacionDTO entity){
        Optional<Estacion> estacion = Stream.of(entity).map(entitymapper).findFirst();
        estacion.ifPresent(estacionRepository::save);
        return estacion.map(DTOmapper).orElseThrow();
    }


    public EstacionDTO update(EstacionDTO entity) {
        Optional<Estacion> estacion = Stream.of(entity).map(entitymapper).findFirst();
        estacion.ifPresent(estacionRepository::save);
        return estacion.map(DTOmapper).orElseThrow();

    }

    public EstacionDTO delete(Long id){
        EstacionDTO estacion = this.getById(id);
        if(estacion != null)
        {
            Optional<Estacion> entity = Stream.of(estacion).map(entitymapper).findFirst();
            entity.ifPresent(estacionRepository::delete);
        }
        return estacion;
    }

    public EstacionDTO getById(Long id) {
        Optional<Estacion> estacion = this.estacionRepository.findById(id);
        return estacion.map(DTOmapper).orElseThrow();
    }

    public List<EstacionDTO> getAll(){
        List<Estacion> estaciones = this.estacionRepository.findAll();
        return estaciones.stream().map(DTOmapper).toList();
    }

    public boolean estacionesExists(Long estacionesId){
        return this.estacionRepository.existsById(estacionesId);
    }
}
