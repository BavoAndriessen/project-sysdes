package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.application.event.ContainerImportModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewContainerListEvent extends DomainEvent {
    private List<ContainerImportModel> containers;

    public NewContainerListEvent(List<ContainerImportModel> containers) {
        super();
        this.containers=containers;
    }
}
