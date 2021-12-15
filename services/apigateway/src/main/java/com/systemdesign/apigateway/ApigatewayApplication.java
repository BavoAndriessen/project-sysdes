package com.systemdesign.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;



@SpringBootApplication
@CrossOrigin(origins="*")
public class ApigatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }

    private String getEnvOrLocalhost(String envName) {
        String env = System.getenv(envName);
        if(env == null) {
            return "localhost";
        }
        return env;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/containers/**").uri(String.format("http://%s:8080/api/containers/", getEnvOrLocalhost("CONTAINER_MANAGEMENT_HOST"))))
                .route(r -> r.path("/api/administration/**").uri(String.format("http://%s:8080/api/administration/", getEnvOrLocalhost("ADMINISTRATION_HOST"))))
                .route(r -> r.path("/api/kapiteinsdienst/**").uri(String.format("http://%s:8080/api/kapiteinsdienst/", getEnvOrLocalhost("KAPITEINSDIENST_HOST"))))
                .route(r -> r.path("/api/berth/**").uri(String.format("http://%s:8080/api/berth/", getEnvOrLocalhost("BERTH_HOST"))))
                .route(r -> r.path("/api/maintenanceservice/**").uri(String.format("http://%s:8080/api/maintenanceservice/", getEnvOrLocalhost("MAINTENANCE_HOST"))))
                .route(r -> r.path("/api/towingpilotage/**").uri(String.format("http://%s:8080/api/towingpilotage/", getEnvOrLocalhost("TOWINGPILOTAGE_HOST"))))
                .route(r -> r.path("/api/vtc/**").uri(String.format("http://%s:8080/api/vtc/", getEnvOrLocalhost("VTC_HOST"))))

                .build();
    }
}
