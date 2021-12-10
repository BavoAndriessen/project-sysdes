package com.projectsysdes.containermanagement.application.event;

import com.projectsysdes.containermanagement.application.ContainerManagementService;
import com.projectsysdes.containermanagement.application.Response;
import com.projectsysdes.containermanagement.domain.Container;
import com.projectsysdes.containermanagement.domain.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventListener {
    @Autowired
    ContainerManagementService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    @TransactionalEventListener
    public void consumeContainerApprovedEvent(ContainerApprovedEvent event) {
        Response r = service.approveContainer(event.getContainerId());
        logger.info(r.getStatus().name() + ": " + r.getMessage());
    }

    @Async
    @TransactionalEventListener
    public void consumeContainerScannedEvent(ContainerScannedEvent event) {
        Response r = service.scanContainer(event.getContainerId(), event.getNewStatus().name(), event.getNewLocation());
        logger.info(r.getStatus().name() + ": " + r.getMessage());
    }

    @Async
    @TransactionalEventListener
    public void consumeArrivedWithContainersEvent(ArrivedWithContainersEvent event) {
        Response r = service.arrivedWithContainers(event.getContainerIds(), event.getLocation());
        logger.info(r.getStatus().name() + ": " + r.getMessage());
    }

    @Async
    @TransactionalEventListener
    public void consumeReadyForContainersEvent(ReadyForContainersEvent event) {
        Response r = service.readyForContainers(event.getContainerIds(), event.getLocation());
        logger.info(r.getStatus().name() + ": " + r.getMessage());
    }

    @Async
    @TransactionalEventListener
    public void consumeNewContainerListEvent(NewContainerListEvent event) {
        List<Container> containers = new ArrayList<>(event.getContainers().size());
        for (ContainerImportModel cim: event.getContainers()) {
            containers.add(new Container(cim.getContainerId(), cim.getContents()));
        }
        Response r = service.registerContainers(containers);
        logger.info(r.getStatus().name() + ": " + r.getMessage());
    }
}
