package com.projectsysdes.containermanagement;

import com.projectsysdes.containermanagement.API.messaging.Channels;
import com.projectsysdes.containermanagement.application.event.EventDispatcher;
import com.projectsysdes.containermanagement.domain.Container;
import com.projectsysdes.containermanagement.domain.ContainerLocation;
import com.projectsysdes.containermanagement.domain.ContainerLocationType;
import com.projectsysdes.containermanagement.domain.ContainerRepository;
import com.projectsysdes.containermanagement.domain.events.ContainersReadyAtDockEvent;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EnableBinding(Channels.class)
@SpringBootApplication
@EnableAsync
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
    CommandLineRunner testMessageOutPutGateway(EventDispatcher ed) {
        return (args) -> {
            logger.info(">Save new container with id {} to database.", 0);
            ed.publishContainersReadyAtDockEvent(new ContainersReadyAtDockEvent(LocalDateTime.now(), List.of(0,1), new ContainerLocation(ContainerLocationType.DOCK, "432")));
        };
    }
}
