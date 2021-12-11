package be.ugent.systemdesign.vesseltrafficcontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VesseltrafficcontrolApplication {

    private static final Logger log = LoggerFactory.getLogger(VesseltrafficcontrolApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VesseltrafficcontrolApplication.class, args);
    }



}
