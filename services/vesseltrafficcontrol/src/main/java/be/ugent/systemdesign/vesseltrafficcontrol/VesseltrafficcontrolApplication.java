package be.ugent.systemdesign.vesseltrafficcontrol;

import be.ugent.systemdesign.vesseltrafficcontrol.api.messaging.Channels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class VesseltrafficcontrolApplication {

    private static final Logger log = LoggerFactory.getLogger(VesseltrafficcontrolApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VesseltrafficcontrolApplication.class, args);
    }



}
