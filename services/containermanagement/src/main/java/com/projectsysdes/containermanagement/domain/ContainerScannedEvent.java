package com.projectsysdes.containermanagement.domain;

import com.projectsysdes.containermanagement.domain.seedwork.DomainEvent;

public class ContainerScannedEvent extends DomainEvent {
    private final int containerId;
    private final ContainerState newStatus;

    public ContainerScannedEvent(int containerId, ContainerState newStatus) {
        this.containerId = containerId;
        this.newStatus = newStatus;
    }
}
