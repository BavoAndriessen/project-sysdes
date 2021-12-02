package com.projectsysdes.containermanagement.domain;

import com.projectsysdes.containermanagement.domain.seedwork.DomainEvent;

public class ContainerScannedEvent extends DomainEvent {
    private final int containerId;
    private final ContainerState newStatus;
    private final ContainerLocation newLocation;

    public ContainerScannedEvent(int containerId, ContainerState newStatus, ContainerLocation newLocation) {
        this.containerId = containerId;
        this.newStatus = newStatus;
        this.newLocation = newLocation;
    }
}
