package com.projectsysdes.containermanagement.domain;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class Container {
    private final Integer containerId;
    private ContainerState state;
    private ContainerLocation currentLocation;
    private final ContainerLocation destinationLocation;

    public Container(int containerId, ContainerLocation destinationLocation) {
        this.containerId = containerId;
        this.currentLocation = new ContainerLocation(ContainerLocationType.UNKNOWN, "");
        this.state = ContainerState.REGISTERED;
        this.destinationLocation = destinationLocation;
    }



    public void arrived(ContainerLocation at) { // with scanContainers message
        if (this.state != ContainerState.REGISTERED) {
            throw new IllegalContainerStateChangeException();
        }
        this.currentLocation = at;
        this.state = ContainerState.ARRIVED;
    }

    public void inTransit() { // with scanContainers message
        if (this.state != ContainerState.ARRIVED) {
            throw new IllegalContainerStateChangeException();
        }
        this.state = ContainerState.TRANSIT_NOT_APPROVED;
    }

    public void approve() throws IllegalContainerStateChangeException {
        // containers can only be approved when in transit
        if (state != ContainerState.TRANSIT_NOT_APPROVED) {
            throw new IllegalContainerStateChangeException();
        }
        state = ContainerState.TRANSIT_APPROVED;
    }

    public void transferToDock(ContainerLocation to) {
        if (this.state != ContainerState.TRANSIT_APPROVED) {
            throw new IllegalContainerStateChangeException();
        }
        this.currentLocation = to;
        this.state = ContainerState.READY_TO_LOAD;
    }

    public void load(ContainerLocation newContainerLocation) {
        if (this.state != ContainerState.READY_TO_LOAD) {
            throw new IllegalContainerStateChangeException();
        }
        this.currentLocation = newContainerLocation;
        this.state = ContainerState.LOADED;
    }

    public void release() {
        if (state != ContainerState.LOADED) {
            throw new IllegalContainerStateChangeException();
        }
        state = ContainerState.RELEASED;
        // or delete from database instead and remove the RELEASED state (same goes for REGISTERED state)
    }

    @Override
    public String toString() {
        return "Container{" +
                "containerId=" + containerId +
                ", state=" + state +
                ", currentLocation=" + currentLocation +
                ", destinationLocation=" + destinationLocation +
                '}';
    }
}
