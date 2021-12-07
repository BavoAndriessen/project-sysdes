package com.projectsysdes.containermanagement.API.messaging;

import com.projectsysdes.containermanagement.application.command.CommandDispatcher;
import com.projectsysdes.containermanagement.application.event.EventDispatcher;
import com.projectsysdes.containermanagement.domain.commands.TransferContainersCommand;
import com.projectsysdes.containermanagement.domain.events.*;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@MessagingGateway
@Service
public interface MessageOutputGateway extends EventDispatcher, CommandDispatcher {

    @Override
    @Gateway(requestChannel = Channels.CONTAINERS_READY_AT_DOCK)
    void publishContainersReadyAtDockEvent(ContainersReadyAtDockEvent event);

    @Override
    @Gateway(requestChannel = Channels.TRANSFER_CONTAINERS_COMMAND)
    void transferContainersCommand(TransferContainersCommand transferContainersCommand);


    // the code below if for testing purposes only, anders niet de bedoeling om deze channels te gebruiken als output channel
//    @Override
//    @Gateway(requestChannel = Channels.ARRIVED_WITH_CONTAINERS)
//    void publishTestEvent(ArrivedWithContainersEvent event);
//
//    @Override
//    @Gateway(requestChannel = Channels.CONTAINER_APPROVED)
//    void publishTestEvent(ContainerApprovedEvent event);
//
//    @Override
//    @Gateway(requestChannel = Channels.CONTAINER_SCANNED)
//    void publishTestEvent(ContainerScannedEvent event);
//
//    @Override
//    @Gateway(requestChannel = Channels.READY_FOR_CONTAINERS)
//    void publishTestEvent(ReadyForContainersEvent event);

}
