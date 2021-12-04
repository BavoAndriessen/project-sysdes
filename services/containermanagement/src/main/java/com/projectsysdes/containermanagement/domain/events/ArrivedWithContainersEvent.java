package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArrivedWithContainersEvent extends DomainEvent {

    private final ContainerLocation location;
    private final List<Integer> containerIds;

    public ArrivedWithContainersEvent(ContainerLocation at, List<Integer> ids) {
        super();
        this.location=at;
        this.containerIds = ids;
    }
}
