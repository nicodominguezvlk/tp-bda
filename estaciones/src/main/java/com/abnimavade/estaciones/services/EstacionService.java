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

    //1. CONSULTAR EL LISTADO DE TODAS LAS ESTACIONES DISPONIBLES EN LA CIUDAD
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

    // 5. AGREGAR UNA NUEVA ESTACIÓN AL SISTEMA
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
    /*public double distanciaHaversina(double latitudCliente, double longitudCliente, double latitud, double longitud) {
        double radioTierra = 6371;
        // Radio medio de la Tierra en kilómetros
        double dLat = Math.toRadians(latitud - latitudCliente);
        double dLon = Math.toRadians(longitud - longitudCliente);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(latitudCliente)) * Math.cos(Math.toRadians(latitud)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c;     } */

    public double distanciaEuclidiana(double latitudCliente, double longitudCliente, double latitud, double longitud) {
        double metrosPorGrado = 110000;

        double dLat = (latitud - latitudCliente) * metrosPorGrado;
        double dLon = (longitud - longitudCliente) * metrosPorGrado;

        double distancia = Math.sqrt(Math.pow(dLat, 2) + Math.pow(dLon, 2));

        return distancia;
    }
    public long getEstacionCercana(double latitud, double longitud){
        List<Estacion> estaciones = estacionRepository.findAll();
        // Encontrar la estación más cercana

        Estacion estacionMasCercana = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Estacion estacion : estaciones) {
            double distancia = distanciaEuclidiana(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionMasCercana = estacion;
            }
        }
        long idEstacionCercana = estacionMasCercana.getEstacionId();
        return idEstacionCercana;
    }
}
