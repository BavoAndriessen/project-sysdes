package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReadyForContainersEvent extends DomainEvent {

    private final ContainerLocation location;
    private final List<Integer> containerIds;

    public ReadyForContainersEvent(List<Integer> containerIds, ContainerLocation location) {
        super();
        this.containerIds = containerIds;
        this.location = location;
    }
}
