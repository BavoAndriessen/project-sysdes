package com.projectsysdes.containermanagement.domain.events;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContainerApprovedEvent extends DomainEvent {

    private final Integer containerId;

    public ContainerApprovedEvent(Integer containerId) {
        super();
        this.containerId=containerId;
    }
}
