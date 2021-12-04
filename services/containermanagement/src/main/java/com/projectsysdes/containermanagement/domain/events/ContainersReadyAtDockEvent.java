package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import lombok.Getter;

import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ContainersReadyAtDockEvent extends DomainEvent {

    private final ContainerLocation location;
    private final List<Integer> containerIds;

    public ContainersReadyAtDockEvent(List<Integer> containerIds, ContainerLocation location) {
        super();
        this.containerIds = containerIds;
        this.location = location;
    }
}
