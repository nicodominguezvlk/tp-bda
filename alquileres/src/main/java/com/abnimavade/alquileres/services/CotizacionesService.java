package com.abnimavade.alquileres.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service("cotizacionesService")
public class CotizacionesService {

    private final String apiUrl = "http://34.82.105.125:8080/convertir";

    public double convertirMoneda(String moneda, double cantidadArs) {
        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body
        String requestBody = String.format(Locale.US, "{\"moneda_destino\":\"%s\",\"importe\":%.2f}", moneda, cantidadArs);

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

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the JSON string
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            convertedValue = jsonNode.get("importe").asDouble();

            return convertedValue;
        }
        catch (JsonProcessingException e){}

        // Extract the "importe" field as a double


        //convertedValue = Double.parseDouble(responseBody.split(":")[1].replaceAll("[^\\d.]", ""));

        return 0;
    }

}
