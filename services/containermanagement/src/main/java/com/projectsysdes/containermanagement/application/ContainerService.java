package com.projectsysdes.containermanagement.application;

import com.projectsysdes.containermanagement.API.REST.ContainerLocationViewModel;

import java.util.List;

public interface ContainerService {

    Response approveContainer(Integer containerId);
    Response arrivedWithContainer(List<Integer> containers, ContainerLocationViewModel at);
    Response readyForContainers(ContainerLocationViewModel location);
    Response scanContainer(Integer containerId, String newState, ContainerLocationViewModel location);


}
