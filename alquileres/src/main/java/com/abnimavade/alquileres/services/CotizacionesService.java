package com.abnimavade.alquileres.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("cotizacionesService")
public class CotizacionesService {

    private final String apiUrl = "http://34.82.105.125:8080/convertir";

    public double convertirMoneda(String moneda, double cantidadArs){
        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body
        String requestBody = String.format("{\"moneda_destino\":\"%s\",\"importe\":%f}", moneda, cantidadArs);

        // Create the request entity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make the HTTP request
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        // Parse the response body to extract the converted currency value
        String responseBody = responseEntity.getBody();
        // Assuming your response always has "importe" as a key
        double convertedValue;
        assert responseBody != null;
        convertedValue = Double.parseDouble(responseBody.split(":")[1].replaceAll("[^\\d.]", ""));

        return convertedValue;
    }

}
