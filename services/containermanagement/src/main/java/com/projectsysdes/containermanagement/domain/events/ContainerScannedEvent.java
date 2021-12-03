package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import com.projectsysdes.containermanagement.domain.ContainerState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContainerScannedEvent extends DomainEvent {
    private final Integer containerId;
    private final ContainerState newStatus;
    private final ContainerLocation newLocation;

    public ContainerScannedEvent(LocalDateTime ldt, Integer containerId, ContainerState newStatus, ContainerLocation newLocation) {
        super(ldt);
        this.containerId = containerId;
        this.newStatus = newStatus;
        this.newLocation = newLocation;
    }
}
