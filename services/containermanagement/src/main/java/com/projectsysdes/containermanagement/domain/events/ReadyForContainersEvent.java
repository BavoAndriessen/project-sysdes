package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReadyForContainersEvent extends DomainEvent {

    private final ContainerLocation location;
    private final List<Integer> containerIds;

    public ReadyForContainersEvent(LocalDateTime createdTime, List<Integer> containerIds, ContainerLocation location) {
        super(createdTime);
        this.containerIds = containerIds;
        this.location = location;
    }
}
