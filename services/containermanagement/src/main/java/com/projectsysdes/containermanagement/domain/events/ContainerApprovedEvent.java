package com.projectsysdes.containermanagement.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ContainerApprovedEvent extends DomainEvent {

    private Integer containerId;

    public ContainerApprovedEvent(Integer containerId) {
        super();
        this.containerId=containerId;
    }
}
