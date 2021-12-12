package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.container.ContainerLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReadyForContainersEvent extends DomainEvent {

    private List<Integer> containerIds;
    private ContainerLocation location;

    public ReadyForContainersEvent(List<Integer> containerIds, ContainerLocation location) {
        super();
        this.containerIds = containerIds;
        this.location = location;
    }
}
