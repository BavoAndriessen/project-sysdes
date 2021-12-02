package be.ugent.systemdesign.kapiteinsdienst;

import be.ugent.systemdesign.kapiteinsdienst.API.Channels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(Channels.class)
@SpringBootApplication
public class KapiteinsdienstApplication {

    public static void main(String[] args) {
        SpringApplication.run(KapiteinsdienstApplication.class, args);
    }

}
