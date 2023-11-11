package com.abnimavade.tp;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/alquileres").uri("http://localhost:8080/alquileres"))
                .route(p -> p.path("/estaciones").uri("http://localhost:8080/estaciones"))
                .route(p -> p.path("/tarifas").uri("http://localhost:8080/tarifas"))
                .build();
    }
}
