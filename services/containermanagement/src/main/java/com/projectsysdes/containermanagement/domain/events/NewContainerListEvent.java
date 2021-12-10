package com.projectsysdes.containermanagement.domain.events;

import com.projectsysdes.containermanagement.application.event.ContainerImportModel;
import lombok.Getter;

import java.util.List;

@Getter
public class NewContainerListEvent extends DomainEvent {
    private List<ContainerImportModel> containers;

    public NewContainerListEvent(List<ContainerImportModel> containers) {
        super();
        this.containers=containers;
    }
}
