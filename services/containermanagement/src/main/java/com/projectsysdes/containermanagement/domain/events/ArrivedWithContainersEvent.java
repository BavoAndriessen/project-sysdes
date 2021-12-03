package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArrivedWithContainersEvent extends DomainEvent {

    private final ContainerLocation at;
    private final List<Integer> containerIds;

    public ArrivedWithContainersEvent(LocalDateTime createdTime, ContainerLocation at, List<Integer> ids) {
        super(createdTime);
        this.at=at;
        this.containerIds = ids;
    }
}
