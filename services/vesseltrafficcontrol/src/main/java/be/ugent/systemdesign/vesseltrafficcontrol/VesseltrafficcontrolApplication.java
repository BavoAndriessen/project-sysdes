package be.ugent.systemdesign.vesseltrafficcontrol;

import be.ugent.systemdesign.vesseltrafficcontrol.api.messaging.Channels;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.IRouteRepository;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Vessel;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.VesselState;
import be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.RouteDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;


@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class VesseltrafficcontrolApplication {

    private static final Logger log = LoggerFactory.getLogger(VesseltrafficcontrolApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(VesseltrafficcontrolApplication.class, args);
    }
/*
    @Bean
    public CommandLineRunner populateDatabase(IRouteRepository repository) {
            return (args) -> {
                Route r0 = new Route(0,"1;2", Size.SMALL, 1200, 4, 1);
                Route r1 = new Route(1,"1;2", Size.SMALL, 1450, 4, 2);
                Route r2 = new Route(2,"1;3;4;5", Size.SMALL, 1600, 2, 3);
                Route r3 = new Route(3,"3;4;5", Size.MEDIUM, 1500, 2, 3);
                Route r4 = new Route(4,"1;3;4;5", Size.SMALL, 1600, 2, 4);
                Route r5 = new Route(5,"3;4;5", Size.MEDIUM, 1500, 2, 4);
                Route r6 = new Route(6,"6;5", Size.MEDIUM, 1320, 3, 4);
                Route r7 = new Route(7,"1;3;4;5", Size.SMALL, 1600, 2, 5);
                Route r8 = new Route(8,"3;4;5", Size.MEDIUM, 1500, 2, 5);
                Route r9 = new Route(9,"6;5", Size.MEDIUM, 1320, 3, 5);
                Route r10 = new Route(10,"6;7", Size.LARGE, 1355, 2, 5);
                Route r11 = new Route(11,"6;7;8", Size.SMALL, 1110, 7, 6);
                Route r12 = new Route(12,"6;9", Size.LARGE, 1223, 4, 6);
                Route r13 = new Route(13,"6;10;9", Size.SMALL, 1345, 8, 6);
                Route r14 = new Route(14,"6;9", Size.LARGE, 1223, 4, 7);
                Route r15 = new Route(15,"6;10;9", Size.SMALL, 1345, 8, 7);
                Route r16 = new Route(16,"6;9", Size.LARGE, 1223, 4, 8);
                Route r17 = new Route(17,"6;10;9", Size.SMALL, 1345, 8, 8);
                Route r18 = new Route(18,"6;9", Size.LARGE, 1223, 4, 9);
                Route r19 = new Route(19,"6;10;9", Size.SMALL, 1345, 8, 9);
                Route r20 = new Route(20,"6;13;14", Size.SMALL, 1623, 4, 9);
                Route r21 = new Route(21,"11;12;13;14", Size.SMALL, 1711, 3, 9);
                Route r22 = new Route(22,"6;13", Size.MEDIUM, 1542, 3, 10);
                Route r23 = new Route(23,"11;12;13", Size.SMALL, 1456, 5, 10);

                repository.save(r0);
                repository.save(r1);
                repository.save(r2);
                repository.save(r3);
                repository.save(r4);
                repository.save(r5);
                repository.save(r6);
                repository.save(r7);
                repository.save(r8);
                repository.save(r9);
                repository.save(r10);
                repository.save(r11);
                repository.save(r12);
                repository.save(r13);
                repository.save(r14);
                repository.save(r15);
                repository.save(r16);
                repository.save(r17);
                repository.save(r18);
                repository.save(r19);
                repository.save(r20);
                repository.save(r21);
                repository.save(r22);
                repository.save(r23);

                String path1 = repository.findOne(Size.SMALL, 5);
                log.warn("Route found via {}", path1);
                String path2 = repository.findOne(Size.MEDIUM, 2);
                log.warn("Route found via {}", path2);
                String path3 = repository.findOne(Size.LARGE, 9);
                log.warn("Route found via {}", path3);
            };
    }
*/

}
