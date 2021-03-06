package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.domain.container.ContainerLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor()
public class ArrivedWithContainersEvent extends DomainEvent {

    private ContainerLocation location;
    private List<Integer> containerIds;

    public ArrivedWithContainersEvent(ContainerLocation location, List<Integer> ids) {
        super();
        this.location=location;
        this.containerIds = ids;
    }
}
