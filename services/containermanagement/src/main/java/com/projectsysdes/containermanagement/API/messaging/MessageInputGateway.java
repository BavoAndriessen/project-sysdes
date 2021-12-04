package com.projectsysdes.containermanagement.API.messaging;

import com.projectsysdes.containermanagement.application.event.EventListener;
import com.projectsysdes.containermanagement.domain.events.ArrivedWithContainersEvent;
import com.projectsysdes.containermanagement.domain.events.ContainerApprovedEvent;
import com.projectsysdes.containermanagement.domain.events.ContainerScannedEvent;
import com.projectsysdes.containermanagement.domain.events.ReadyForContainersEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    @Autowired
    EventListener eventListener;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(Channels.CONTAINER_APPROVED)
    void consumeContainerApprovedEvent(ContainerApprovedEvent event) {
        eventListener.consumeContainerApprovedEvent(event);
    }

    @StreamListener(Channels.CONTAINER_SCANNED)
    void consumeContainerScannedEvent(ContainerScannedEvent event) {
        eventListener.consumeContainerScannedEvent(event);
    }

    @StreamListener(Channels.ARRIVED_WITH_CONTAINERS)
    void consumeArrivedWithContainersEvent(ArrivedWithContainersEvent event) {
        eventListener.consumeArrivedWithContainersEvent(event);
    }

    @StreamListener(Channels.READY_FOR_CONTAINERS)
    void consumeReadyForContainersEvent(ReadyForContainersEvent event) {
        eventListener.consumeReadyForContainersEvent(event);
    }
}
