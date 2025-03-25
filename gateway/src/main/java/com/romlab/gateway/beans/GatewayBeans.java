package com.romlab.gateway.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayBeans {

    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder) {
        /* Esta es la forma de generar las rutas estaticas de nuestros
         * microservicios desde el gateway hacia los microservicios
         */
        return builder.routes()
                .route(route -> route
                        .path("/api/companies/company/*")
                        .uri("http://localhost:8081"))
                .route(route -> route
                        .path("/api/report/report/*")
                        .uri("http://localhost:7070"))
                .build();
    }

    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaOn(RouteLocatorBuilder builder) {
        /* Esta es la forma de generar las rutas dinamicas de nuestros
         * microservicios desde el gateway hacia los microservicios
         * pero teniendo siempre el habilitado el puerto dinamico
         * port: 0
         */
        return builder.routes()
                .route("companies", r -> r.path("/api/companies/**")
                        .uri("lb://companies"))
                .route("report", r -> r.path("/api/report/**")
                        .uri("lb://report-ms"))
                .build();
    }

}
