package com.projectsysdes.containermanagement.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Container {
    private final int containerId;
    private ContainerState state;
    private ContainerLocation currentLocation;
    private final ContainerLocation destinationLocation;

    public void ApproveContainer() throws IllegalContainerStateChangeException {
        // containers ccna only be approved when in transit
        if (state == ContainerState.TRANSIT_NOT_APPROVED) {
            state = ContainerState.TRANSIT_APPROVED;
        } else {
            throw new IllegalContainerStateChangeException();
        }
    }



}
