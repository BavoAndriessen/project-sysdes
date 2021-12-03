package com.projectsysdes.containermanagement.domain.events;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContainerApprovedEvent extends DomainEvent {

    private final Integer containerId;

    public ContainerApprovedEvent(LocalDateTime createdTime, Integer containerId) {
        super(createdTime);
        this.containerId=containerId;
    }
}
