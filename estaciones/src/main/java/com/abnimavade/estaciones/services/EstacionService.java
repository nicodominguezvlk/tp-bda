package com.abnimavade.estaciones.services;

import com.abnimavade.estaciones.dtos.EstacionDTO;
import com.abnimavade.estaciones.models.Estacion;
import com.abnimavade.estaciones.repos.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// VALE: Para los service, especificamos el @Service
@Service
public class EstacionService {

    @Autowired
    private EstacionRepository estacionRepository;

    public List<EstacionDTO> getAll() {
        List<Estacion> estaciones = estacionRepository.findAll();
        return estaciones.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EstacionDTO convertToDto(Estacion estacion) {
        EstacionDTO estacionDTO = new EstacionDTO();
        // Id de la estacion
        estacionDTO.setEstacionId(estacion.getEstacionId());
        // Nombre de la estacion
        estacionDTO.setNombre(estacion.getNombre());
        // Fecha hora Creacion
        estacionDTO.setFechaHoraCreacion(estacion.getFechaHoraCreacion());
        // Latitud y Longitud
        estacionDTO.setLatitud(estacion.getLatitud());
        estacionDTO.setLongitud(estacion.getLongitud());
        return estacionDTO;
    }


    public EstacionDTO create(EstacionDTO estacionDto) {
        Estacion estacion = new Estacion();
        // Nombre de la estacion
        estacion.setNombre(estacionDto.getNombre());
        // Fecha hora Creacion
        estacion.setFechaHoraCreacion(estacionDto.getFechaHoraCreacion());
        // Latitud y Longitud
        estacion.setLatitud(estacionDto.getLatitud());
        estacion.setLongitud(estacionDto.getLongitud());
        estacion = estacionRepository.save(estacion);
        estacionDto.setEstacionId(estacion.getEstacionId());
        return estacionDto;
    }



    public EstacionDTO update(Long id,EstacionDTO estacionDTO) {
        // Recibimos el id de la estacion a modificar
        Estacion estacion = estacionRepository.findById(id).get();
        // Nombre de la estacion
        estacion.setNombre(estacionDTO.getNombre());
        // Fecha hora Creacion
        estacion.setFechaHoraCreacion(estacionDTO.getFechaHoraCreacion());
        // Latitud y Longitud
        estacion.setLatitud(estacionDTO.getLatitud());
        estacion.setLongitud(estacionDTO.getLongitud());
        estacion = estacionRepository.save(estacion);
        estacionDTO.setEstacionId(estacion.getEstacionId());
        return estacionDTO;
    }

    public String delete(Long id) {
        estacionRepository.deleteById(id);
        return "Estacion deleted";
    }
}
