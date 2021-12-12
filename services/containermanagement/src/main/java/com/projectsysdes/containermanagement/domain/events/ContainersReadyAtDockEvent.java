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
    private List<Integer> containerIds;

    public ContainersReadyAtDockEvent(List<Integer> containerIds, Integer berthIdentifier) {
        super();
        this.containerIds = containerIds;
        this.berthIdentifier = berthIdentifier;
    }
}
