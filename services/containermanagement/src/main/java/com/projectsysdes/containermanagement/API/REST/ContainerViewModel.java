package com.projectsysdes.containermanagement.API.REST;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContainerViewModel {

    private final Integer containerId;
    private final String contents;
    private final String state;
    private final ContainerLocation currentLocation;
    private final ContainerLocation destinationLocation;

}
