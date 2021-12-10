package be.ugent.systemdesign.maintenanceservice;

import be.ugent.systemdesign.maintenanceservice.API.messaging.Channels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Channels.class)
public class MaintenanceserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaintenanceserviceApplication.class, args);
    }

}
