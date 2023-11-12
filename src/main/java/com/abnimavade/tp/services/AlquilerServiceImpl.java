package com.abnimavade.tp.services;

import com.abnimavade.tp.dtos.AlquilerDTO;
import com.abnimavade.tp.models.Alquiler;
import com.abnimavade.tp.repositories.AlquilerRepository;
import com.abnimavade.tp.services.mappers.AlquilerDTOMapper;
import com.abnimavade.tp.services.mappers.AlquilerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final AlquilerDTOMapper DTOmapper;
    private final AlquilerMapper entitymapper;

    public AlquilerServiceImpl(AlquilerRepository alquilerRepository, AlquilerDTOMapper DTOmapper, AlquilerMapper entitymapper)
    {
        this.alquilerRepository = alquilerRepository;
        this.DTOmapper = DTOmapper;
        this.entitymapper = entitymapper;
    }


    public AlquilerDTO add(AlquilerDTO entity){
        Optional<Alquiler> alquiler = Stream.of(entity).map(entitymapper).findFirst();
        alquiler.ifPresent(alquilerRepository::save);
        return alquiler.map(DTOmapper).orElseThrow();
    }


    public AlquilerDTO update(AlquilerDTO entity) {
        Optional<Alquiler> alquiler = Stream.of(entity).map(entitymapper).findFirst();
        alquiler.ifPresent(alquilerRepository::save);
        return alquiler.map(DTOmapper).orElseThrow();

    }

    public AlquilerDTO delete(Long id){
        AlquilerDTO alquiler = this.getById(id);
        if(alquiler != null)
        {
            Optional<Alquiler> entity = Stream.of(alquiler).map(entitymapper).findFirst();
            entity.ifPresent(alquilerRepository::delete);
        }
        return alquiler;
    }

    public AlquilerDTO getById(Long id) {
        Optional<Alquiler> alquiler = this.alquilerRepository.findById(id);
        return alquiler.map(DTOmapper).orElseThrow();
    }

    @Override
    public List<AlquilerDTO> getAll(){
        List<Alquiler> alquileres = this.alquilerRepository.findAll();
        return alquileres.stream().map(DTOmapper).toList();
    }

    public boolean alquileresExists(Long alquileresId){
        return this.alquilerRepository.existsById(alquileresId);
    }
}
