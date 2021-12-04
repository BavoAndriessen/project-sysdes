package com.projectsysdes.containermanagement.application.query;

import com.projectsysdes.containermanagement.API.REST.ContainerViewModel;
import com.projectsysdes.containermanagement.domain.ContainerLocation;

import java.util.List;

public interface ContainerQuery {
    ContainerLocation getContainerLocation(Integer containerId);
    List<ContainerViewModel> searchContainersForContents(String contents);
}
