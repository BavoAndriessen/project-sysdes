package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.container.ContainerLocation;
import com.projectsysdes.containermanagement.domain.container.ContainerState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContainerScannedEvent extends DomainEvent {
    private Integer containerId;
    private ContainerState newStatus;
    private ContainerLocation newLocation;

    public ContainerScannedEvent(Integer containerId, ContainerState newStatus, ContainerLocation newLocation) {
        super();
        this.containerId = containerId;
        this.newStatus = newStatus;
        this.newLocation = newLocation;
    }
}
