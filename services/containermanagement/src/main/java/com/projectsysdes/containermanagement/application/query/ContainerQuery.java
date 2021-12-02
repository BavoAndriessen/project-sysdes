package com.projectsysdes.containermanagement.application.query;

import com.projectsysdes.containermanagement.API.REST.ContainerLocationViewModel;
import com.projectsysdes.containermanagement.API.REST.ContainerViewModel;

import java.util.List;

public interface ContainerQuery {
    ContainerLocationViewModel getContainerLocation(Integer containerId);
    List<ContainerViewModel> searchContainersForContents(String contents);
}
