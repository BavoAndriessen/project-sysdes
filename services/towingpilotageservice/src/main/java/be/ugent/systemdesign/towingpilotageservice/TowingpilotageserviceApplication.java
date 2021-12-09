package be.ugent.systemdesign.towingpilotageservice;

import be.ugent.systemdesign.towingpilotageservice.API.Channels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Channels.class)
public class TowingpilotageserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TowingpilotageserviceApplication.class, args);
    }

}
