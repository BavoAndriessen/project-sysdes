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

}
