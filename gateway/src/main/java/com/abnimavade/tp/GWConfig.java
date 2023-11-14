package com.abnimavade.tp;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/alquileres").uri("http://localhost:8080/alquileres"))
                .route(p -> p.path("/estaciones").uri("http://localhost:8080/estaciones"))
                .route(p -> p.path("/tarifas").uri("http://localhost:8080/tarifas"))
                .build();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws
            Exception{
        http.authorizeExchange(exchanges -> exchanges

                // Acceso publico
                .pathMatchers("/recursos-publicos/**")
                .permitAll()

                // POST solo admins, GET solo clientes
                .pathMatchers(HttpMethod.GET, "/protegido-a/**")
                .hasRole("CLIENTE")
                .pathMatchers(HttpMethod.POST, "/protegido-a/**")
                .hasRole("ADMIN")

                // POST solo clientes, GET solo admins
                .pathMatchers(HttpMethod.GET, "/protegido-b/**")
                .hasRole("ADMIN")
                .pathMatchers(HttpMethod.POST, "/protegido-b/**")
                .hasRole("CLIENTE")

                // POST solo clientes
                .pathMatchers(HttpMethod.POST, "/protegido-c/**")
                .hasRole("CLIENTE")

                // Cualquier otra petición
                .anyExchange()
                .authenticated()
        ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Se especifica el nombre del claim a analizar
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        // Se agrega este prefijo en la conversión por una convención de Spring
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        // Se asocia el conversor de Authorities al Bean que convierte el token JWT a un objeto Authorization
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));
        // También se puede cambiar el claim que corresponde al nombre que luego se utilizará en el objeto
        // Authorization
        // jwtAuthenticationConverter.setPrincipalClaimName("user_name");

        return jwtAuthenticationConverter;
    }
}