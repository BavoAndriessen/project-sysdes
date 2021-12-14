package com.projectsysdes.containermanagement.application.event;

import com.projectsysdes.containermanagement.application.ContainerManagementService;
import com.projectsysdes.containermanagement.application.Response;
import com.projectsysdes.containermanagement.application.ResponseStatus;
import com.projectsysdes.containermanagement.domain.container.Container;
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
public class EventHandler {
    @Autowired
    ContainerManagementService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    @TransactionalEventListener
    public Response consumeContainerApprovedEvent(ContainerApprovedEvent event) {
        try {
            Response r = service.approveContainer(event.getContainerId());
            logger.info(r.getStatus().name() + ": " + r.getMessage());
            return r;
        } catch (Exception uniqueConstrainViolatedException) {
            // niet erg: command bestaat gewoon al
            return new Response("command already existed", ResponseStatus.SUCCESS);
        }
    }

    @Async
    @TransactionalEventListener
    public Response consumeContainerScannedEvent(ContainerScannedEvent event) {
        Response r = service.scanContainer(event.getContainerId(), event.getNewStatus().name(), event.getNewLocation());
        logger.info(r.getStatus().name() + ": " + r.getMessage());
        return r;
    }

    @Async
    @TransactionalEventListener
    public Response consumeArrivedWithContainersEvent(ArrivedWithContainersEvent event) {
        Response r = service.arrivedWithContainers(event.getContainerIds(), event.getLocation());
        logger.info(r.getStatus().name() + ": " + r.getMessage());
        return r;
    }

    @Async
    @TransactionalEventListener
    public Response consumeReadyForContainersEvent(ReadyForContainersEvent event) {
        try {
            Response r = service.readyForContainers(event.getContainerIds(), event.getLocation());
            logger.info(r.getStatus().name() + ": " + r.getMessage());
            return r;
        } catch (Exception uniqueConstrainViolatedException) {
            // niet erg: command bestaat gewoon al
            return new Response("command already existed", ResponseStatus.SUCCESS);
        }
    }

    @Async
    @TransactionalEventListener
    public Response consumeNewContainerListEvent(NewContainerListEvent event) {
        List<Container> containers = new ArrayList<>(event.getContainerList().size());
        for (ContainerImportModel cim: event.getContainerList()) {
            containers.add(new Container(cim.getContainerId(), cim.getContents()));
        }
        Response r = service.registerContainers(containers);
        logger.info(r.getStatus().name() + ": " + r.getMessage());
        return r;
    }
}
