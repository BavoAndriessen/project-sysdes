package com.projectsysdes.containermanagement;

import com.projectsysdes.containermanagement.API.messaging.Channels;
import com.projectsysdes.containermanagement.application.ContainerManagementService;
import com.projectsysdes.containermanagement.application.command.CommandDispatcher;
import com.projectsysdes.containermanagement.application.event.EventDispatcher;
import com.projectsysdes.containermanagement.domain.*;
import com.projectsysdes.containermanagement.domain.commands.TransferContainersCommand;
import com.projectsysdes.containermanagement.domain.events.*;
import com.projectsysdes.containermanagement.infrastructure.ContainerDataModelRepository;
import com.projectsysdes.containermanagement.infrastructure.ContainerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@EnableBinding(Channels.class)
@SpringBootApplication
//@EnableAsync
public class ContainermanagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(ContainermanagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ContainermanagementApplication.class, args);
    }

    @Bean
    CommandLineRunner testContainerRepo(ContainerRepository repo, ContainerDataModelRepository cdmrepo) {
        return (args) -> {
            logger.info(">Save new container with id {} to database.", 0);
            repo.save(new Container(0, "hash", new ContainerLocation(ContainerLocationType.SHIP, "WR057")));

            logger.info(">Find one container by id {} from database.", 0);
            try {
                Container c = repo.find(0);
                logger.info(c.toString());
            } catch (ContainerNotFoundException e) {
                logger.error("container with id 0 not found");
            }
            List<Container> containers = repo.findAll();
            logger.info(String.valueOf(containers.size()));
            cdmrepo.findAll();

        };
    }
    @Bean
    CommandLineRunner testMessageOutPutGateway(EventDispatcher ed, CommandDispatcher cd) {
        return (args) -> {
            logger.info("Test publish ContainersReadyAtDockEvent and send TransferContainersCommand");
            ed.publishContainersReadyAtDockEvent(new ContainersReadyAtDockEvent(List.of(0,1), new ContainerLocation(ContainerLocationType.DOCK, "432")));
            cd.transferContainersCommand(new TransferContainersCommand(List.of(0,1,2), new ContainerLocation(ContainerLocationType.SHIP, "303")));

            // the code below if for testing purposes only, anders niet de bedoeling om deze channels te gebruiken als output channel
//            logger.info(">Testing some input events");
//            ed.publishTestEvent(new ArrivedWithContainersEvent(new ContainerLocation(ContainerLocationType.SHIP, "303"), List.of(0,1,2,3,4)));
//            ed.publishTestEvent(new ContainerScannedEvent(0, ContainerState.TRANSIT_NOT_APPROVED, new ContainerLocation(ContainerLocationType.TRANSIT, "324")));
//            ed.publishTestEvent(new ContainerApprovedEvent(0));
//            ed.publishTestEvent(new ReadyForContainersEvent(List.of(1,2,3,4), new ContainerLocation(ContainerLocationType.EXTERNAL_TRANSPORT, "6543")));
        };
    }

//    @Bean
//    CommandLineRunner testEventHandling(EventDispatcher eventDispatcher) {
//        return (args) -> {
//        };
//    }

}
