package com.projectsysdes.containermanagement.API.REST;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContainerViewModel {

    private final Integer containerId;
    private final String contents;
    private String state;
    private ContainerLocationViewModel currentLocation;
    private final ContainerLocationViewModel destinationLocation;

}
