package com.abnimavade.alquileres.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("estacionService")
public class EstacionService {
    public Double getDistanciaEstaciones(long idEstRetiro, int idEstDevolucion) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/estaciones/getDistanciaEstaciones/" + Long.toString(idEstRetiro) + "/" + Integer.toString(idEstDevolucion);
        return restTemplate.getForObject(url, Double.class);
    }
}
