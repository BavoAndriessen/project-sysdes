package com.systemdesign.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayApplication {

    @Value("${containers}")
    private String containers;

    @Value("${administration}")
    private String administration;

    @Value("${kapiteinsdienst}")
    private String kapiteinsdienst;

    @Value("${berth}")
    private String berth;

    @Value("${maintenanceservice}")
    private String maintenanceservice;

    @Value("${towingpilotage}")
    private String towingpilotage;

    @Value("${vtc}")
    private String vtc;

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.host("*").and().path("/api/containers/**").uri("http://" + containers + "/api/containers/"))
                .route(r -> r.host("*").and().path("/api/administration/**").uri("http://" + containers + "/api/administration/"))
                .route(r -> r.host("*").and().path("/api/kapiteinsdienst/**").uri("http://" + containers + "/api/kapiteinsdienst/"))
                .route(r -> r.host("*").and().path("/api/berth/**").uri("http://" + containers + "/api/berth/"))
                .route(r -> r.host("*").and().path("/api/maintenanceservice/**").uri("http://" + containers + "/api/maintenanceservice/"))
                .route(r -> r.host("*").and().path("/api/towingpilotage/**").uri("http://" + containers + "/api/towingpilotage/"))
                .route(r -> r.host("*").and().path("/api/vtc/**").uri("http://" + containers + "/api/vtc/"))

                .build();
    }
}
