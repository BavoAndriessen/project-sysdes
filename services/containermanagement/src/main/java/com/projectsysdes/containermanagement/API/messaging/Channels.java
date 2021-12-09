package com.projectsysdes.containermanagement.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    // consume events
    String CONTAINER_SCANNED = "container_scanned";
    String CONTAINER_APPROVED = "container_approved";
    String ARRIVED_WITH_CONTAINERS = "arrived_with_containers";
    String READY_FOR_CONTAINERS = "ready_for_containers";

    @Input(CONTAINER_SCANNED)
    MessageChannel containerScanned();

    @Input(CONTAINER_APPROVED)
    MessageChannel containerApproved();

    @Input(ARRIVED_WITH_CONTAINERS)
    MessageChannel arrivedWithContainers();

    @Input(READY_FOR_CONTAINERS)
    MessageChannel readyForContainers();


    // publish events
    String CONTAINERS_READY_AT_DOCK = "containers_ready_at_dock";
    String TRANSFER_CONTAINERS_COMMAND = "transfer_containers_command";

    @Output(CONTAINERS_READY_AT_DOCK)
    MessageChannel containersReadyAtDock();

    @Output(TRANSFER_CONTAINERS_COMMAND)
    MessageChannel transferContainers();

}
