package com.projectsysdes.containermanagement.API.messaging;

import com.projectsysdes.containermanagement.application.event.EventDispatcher;
import com.projectsysdes.containermanagement.domain.events.ContainersReadyAtDockEvent;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.kafka.streams.HeaderEnricher;
import org.springframework.stereotype.Service;

@MessagingGateway
@Service
public interface MessageOutputGateway extends EventDispatcher {

    @Gateway(requestChannel = Channels.CONTAINERS_READY_AT_DOCK)
    @Override
    void publishContainersReadyAtDockEvent(ContainersReadyAtDockEvent event);

}
