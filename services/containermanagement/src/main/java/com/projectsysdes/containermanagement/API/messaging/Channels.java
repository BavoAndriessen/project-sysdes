package com.projectsysdes.containermanagement.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    // consume events
    String NEW_CONTAINER_LIST = "new_containers";

    @Input(NEW_CONTAINER_LIST)
    MessageChannel newContainerList();



    // publish events
    String CONTAINERS_READY_AT_DOCK = "containers_ready_at_dock";

    @Output(CONTAINERS_READY_AT_DOCK)
    MessageChannel containersReadyAtDock();

}
