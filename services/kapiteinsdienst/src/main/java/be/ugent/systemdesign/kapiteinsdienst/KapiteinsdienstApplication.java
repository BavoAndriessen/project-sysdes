package be.ugent.systemdesign.kapiteinsdienst;

import be.ugent.systemdesign.kapiteinsdienst.API.Channels;
import be.ugent.systemdesign.kapiteinsdienst.domain.Container;
import be.ugent.systemdesign.kapiteinsdienst.domain.Crew;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import be.ugent.systemdesign.kapiteinsdienst.infrastructure.VesselDataModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableBinding(Channels.class)
@SpringBootApplication
public class KapiteinsdienstApplication {

    public static void main(String[] args) {
        SpringApplication.run(KapiteinsdienstApplication.class, args);
    }
    private static final Logger log = LoggerFactory.getLogger(KapiteinsdienstApplication.class);

    @Bean
    public CommandLineRunner populateDatabase(VesselDataModelRepository DMrepo, VesselRepository repo) {
        return (args) -> {
            DMrepo.deleteAll();

            Vessel vessel = new Vessel("12", LocalDateTime.now(),LocalDateTime.now(),125.0,10.5);
            Container container1 = new Container(1,"bananas");
            Container container2 = new Container(2,"bananas");
            Container container3 = new Container(3,"bananas");
            /*Container container1 = new Container("bananas");
            Container container2 = new Container("bananas");
            Container container3 = new Container("bananas");*/
            List<Container> containerList = new ArrayList<>();
            containerList.add(container1);
            containerList.add(container2);
            containerList.add(container3);

            Crew crew1 = new Crew(1,"jan","last", LocalDate.now(),"type");
            Crew crew2 = new Crew(2,"tom","last", LocalDate.now(),"type");
            Crew crew3 = new Crew(3,"bart","last", LocalDate.now(),"type");
            List<Crew> crewList = new ArrayList<>();
            crewList.add(crew1);
            crewList.add(crew2);
            crewList.add(crew3);

            List<String> services = Arrays.asList("service1", "service2");
            vessel.addAdditionalServices(services);
            vessel.addCrewList(crewList);
            vessel.addContainerList(containerList);

            repo.save(vessel);

            Vessel vesselInRepo = repo.findById("12");
            log.warn("Saved new vessel {} with crewmemeber {} and service {} and container {} and {}", vesselInRepo.getVesselId(),vesselInRepo.getCrewList().get(1).getFirstName(), vesselInRepo.getAdditionalServices().get(0),vesselInRepo.getContainerList().get(0).getContainerId(),vesselInRepo.getContainerList().get(1).getContainerId());
        };
    }

}
