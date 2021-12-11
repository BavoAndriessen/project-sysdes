package com.projectsysdes.containermanagement.application.event;

import com.projectsysdes.containermanagement.domain.events.*;
import org.springframework.stereotype.Service;


public interface EventDispatcher {
    void publishContainersReadyAtDockEvent(ContainersReadyAtDockEvent event);

}
