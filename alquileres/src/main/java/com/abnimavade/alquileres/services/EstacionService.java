package com.abnimavade.alquileres.services;

import org.springframework.web.client.RestTemplate;

public class EstacionService {
    public Double getDistanciaEstaciones(long idEstRetiro, int idEstDevolucion) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/getDistanciaEstaciones?idEstRetiro=" + idEstRetiro + "&idEstDevolucion=" + idEstDevolucion;
        return restTemplate.getForObject(url, Double.class);
    }
}
