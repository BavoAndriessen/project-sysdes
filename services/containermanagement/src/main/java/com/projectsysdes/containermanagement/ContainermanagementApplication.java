package com.projectsysdes.containermanagement;

import com.projectsysdes.containermanagement.domain.Container;
import com.projectsysdes.containermanagement.domain.ContainerLocation;
import com.projectsysdes.containermanagement.domain.ContainerLocationType;
import com.projectsysdes.containermanagement.domain.ContainerRepository;
import com.projectsysdes.containermanagement.infrastructure.ContainerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ContainermanagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(ContainermanagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ContainermanagementApplication.class, args);
    }

    @Bean
    CommandLineRunner testContainerRepo(ContainerRepository repo) {
        return (args) -> {
            logger.info(">Save new container with id {} to database.", 0);
            repo.save(new Container(0, new ContainerLocation(ContainerLocationType.SHIP, "WR057")));

            logger.info(">Find one container by id {} from database.", 0);
            try {
                Container c = repo.find(0);
                logger.info(c.toString());
            } catch (ContainerNotFoundException e) {
                logger.error("container with id 0 not found");
            }
            List<Container> containers = repo.findAll();
            logger.info(String.valueOf(containers.size()));

        };
    }
}
