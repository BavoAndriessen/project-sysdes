package com.projectsysdes.containermanagement.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContainersReadyAtDockEvent extends DomainEvent {

    private Integer berthIdentifier;

    public ContainersReadyAtDockEvent(Integer berthIdentifier) {
        super();
        this.berthIdentifier = berthIdentifier;
    }
}
