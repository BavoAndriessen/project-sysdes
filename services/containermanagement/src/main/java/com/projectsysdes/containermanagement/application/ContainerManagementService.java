package com.projectsysdes.containermanagement.application;

import com.projectsysdes.containermanagement.domain.container.Container;
import com.projectsysdes.containermanagement.domain.container.ContainerLocation;

import java.util.List;

public interface ContainerManagementService {

    Response approveContainer(Integer containerId);
    Response arrivedWithContainers(List<Integer> containers, ContainerLocation at);
    Response readyForContainers(List<Integer> containers, ContainerLocation location);
    Response scanContainer(Integer containerId, String newState, ContainerLocation location);
    Response registerContainers(List<Container> containers);

}
