package com.projectsysdes.containermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectsysdes.containermanagement.API.messaging.Channels;
import com.projectsysdes.containermanagement.application.query.CommandQuery;
import com.projectsysdes.containermanagement.domain.command.TransferContainerCommand;
import com.projectsysdes.containermanagement.domain.container.*;
import com.projectsysdes.containermanagement.domain.events.*;
import com.projectsysdes.containermanagement.infrastructure.container.ContainerDataModelRepository;
import com.projectsysdes.containermanagement.infrastructure.container.ContainerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

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
            repo.save(new Container(0, "hash"));

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
    CommandLineRunner testTransferContainerCommandLogic(ContainerRepository repo, CommandQuery commandQuery) {

        return (args) -> {
            logger.info(">Save new container with id {} to database.", 1);
            repo.save(new Container(1, "crack"));
            // send arrived event
            sendPOSTRequest(new ArrivedWithContainersEvent(new ContainerLocation(ContainerLocationType.SHIP, 303), List.of(1)), "arrived");
            // send scan event -> transit
            sendPOSTRequest(new ContainerScannedEvent(1, ContainerState.TRANSIT_NOT_APPROVED, new ContainerLocation(ContainerLocationType.TRANSIT, 3456)), "scan");

            // following 2 steps are interchangeable
            // send radyforcontainers event
            sendPOSTRequest(new ReadyForContainersEvent(List.of(1), new ContainerLocation(ContainerLocationType.EXTERNAL_TRANSPORT, 678)), "ready");
            // send scan event -> approved
            sendPOSTRequest(new ContainerApprovedEvent(1), "approve");

            // scan => READY_AT_DOCK
            sendPOSTRequest(new ContainerScannedEvent(1, ContainerState.READY_AT_DOCK, new ContainerLocation(ContainerLocationType.EXTERNAL_TRANSPORT, 678)), "scan");

            // TODO: verdere logica uittesten (van containerstate RELEASE bv)

            // nu zou er een event command moeten aangemaakt worden
            logger.info("> testing TransferCommands in repo");
            List<TransferContainerCommand> commands = commandQuery.findAllTransferContainerCommands();
            logger.info("number of commands in repo: " + commands.size());
        };
    }

    private static void sendPOSTRequest(DomainEvent o, String to) {
        try {
            o.setCreatedTime(null);
            var objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                    .writeValueAsString(o);

            logger.info("Sending: " + requestBody);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/containers/" + to))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .headers("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            logger.info("Response: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Bean
//    CommandLineRunner testMessageOutPutGateway(EventDispatcher ed, CommandDispatcher cd) {
//        return (args) -> {
//            logger.info("Test publish ContainersReadyAtDockEvent and send TransferContainersCommand");
//            ed.publishContainersReadyAtDockEvent(new ContainersReadyAtDockEvent(List.of(0,1), Integer.parseInt(new ContainerLocation(ContainerLocationType.DOCK, "303").getLocationIdentifier())));
//            cd.transferContainersCommand(new TransferContainersCommand(List.of(0,1,2), new ContainerLocation(ContainerLocationType.SHIP, "303")));
//
//            // the code below if for testing purposes only, anders niet de bedoeling om deze channels te gebruiken als output channel
////            logger.info(">Testing some input events");
////            ed.publishTestEvent(new ArrivedWithContainersEvent(new ContainerLocation(ContainerLocationType.SHIP, "303"), List.of(0,1,2,3,4)));
////            ed.publishTestEvent(new ContainerScannedEvent(0, ContainerState.TRANSIT_NOT_APPROVED, new ContainerLocation(ContainerLocationType.TRANSIT, "324")));
////            ed.publishTestEvent(new ContainerApprovedEvent(0));
////            ed.publishTestEvent(new ReadyForContainersEvent(List.of(1,2,3,4), new ContainerLocation(ContainerLocationType.EXTERNAL_TRANSPORT, "6543")));
//        };
//    }
}
