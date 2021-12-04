package com.projectsysdes.containermanagement.application.event;

import com.projectsysdes.containermanagement.domain.events.*;
import org.springframework.stereotype.Service;


public interface EventDispatcher {
    void publishContainersReadyAtDockEvent(ContainersReadyAtDockEvent event);

    // the code below if for testing purposes only, anders niet de bedoeling om deze channels te gebruiken als output channel
//    void publishTestEvent(ArrivedWithContainersEvent event);
//    void publishTestEvent(ContainerApprovedEvent event);
//    void publishTestEvent(ContainerScannedEvent event);
//    void publishTestEvent(ReadyForContainersEvent event);
}
