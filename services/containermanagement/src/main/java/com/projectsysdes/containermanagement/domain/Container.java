package com.projectsysdes.containermanagement.domain;


import com.projectsysdes.containermanagement.domain.exceptions.ContainerLocationNotProvidedException;
import com.projectsysdes.containermanagement.domain.exceptions.IllegalContainerStateChangeException;
import lombok.*;

import static com.projectsysdes.containermanagement.domain.ContainerState.*;

@Getter
@Builder
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class Container {
    private final Integer containerId;
    private final String contents;
    private ContainerState state;
    private ContainerLocation currentLocation;
    private ContainerLocation destinationLocation;
    private boolean destinationLocationReady;

    public Container(int containerId, String contents, ContainerLocation destinationLocation) {
        this.containerId = containerId;
        this.contents = contents;
        this.currentLocation = new ContainerLocation(ContainerLocationType.UNKNOWN, "");
        this.state = REGISTERED;
        this.destinationLocation = destinationLocation;
        this.destinationLocationReady = false;
    }



//    public void arrived(ContainerLocation at) { // with scanContainers message
//        if (this.state != ContainerState.REGISTERED) {
//            throw new IllegalContainerStateChangeException();
//        }
//        this.currentLocation = at;
//        this.state = ContainerState.ARRIVED;
//    }
//
//    public void inTransit() { // with scanContainers message
//        if (this.state != ContainerState.ARRIVED) {
//            throw new IllegalContainerStateChangeException();
//        }
//        this.state = ContainerState.TRANSIT_NOT_APPROVED;
//    }
//
//    public void approve() throws IllegalContainerStateChangeException {
//        // containers can only be approved when in transit
//        if (state != ContainerState.TRANSIT_NOT_APPROVED) {
//            throw new IllegalContainerStateChangeException();
//        }
//        state = ContainerState.TRANSIT_APPROVED;
//    }
//
//    public void transferToDock(ContainerLocation to) {
//        if (this.state != ContainerState.TRANSIT_APPROVED) {
//            throw new IllegalContainerStateChangeException();
//        }
//        this.currentLocation = to;
//        this.state = ContainerState.READY_TO_LOAD;
//    }
//
//    public void load(ContainerLocation newContainerLocation) {
//        if (this.state != ContainerState.READY_TO_LOAD) {
//            throw new IllegalContainerStateChangeException();
//        }
//        this.currentLocation = newContainerLocation;
//        this.state = ContainerState.LOADED;
//    }
//
//    public void release() {
//        if (state != ContainerState.LOADED) {
//            throw new IllegalContainerStateChangeException();
//        }
//        state = ContainerState.RELEASED;
//        // or delete from database instead and remove the RELEASED state (same goes for REGISTERED state)
//    }

    // alternative methods for all previous methods to change state
    public void progressState(ContainerState newState, ContainerLocation newLocation) throws ContainerLocationNotProvidedException, IllegalContainerStateChangeException {
        if (!getAllowedStateChanges().get(state).contains(newState)) {
            throw new IllegalContainerStateChangeException();
        }
        state = newState;
        if (getRequiresNewLocation().get(newState)) {
            if (newLocation == null) {
                throw new ContainerLocationNotProvidedException();
            }
            this.currentLocation = newLocation;
        }
    }

    public void setDestinationLocationReady(ContainerLocation location) {
        this.destinationLocationReady = true;
        // voor als die nog niet gekend was
        this.destinationLocation = location;
    }

    @Override
    public String toString() {
        return "Container{" +
                "containerId=" + containerId +
                ", contents='" + contents + '\'' +
                ", state=" + state +
                ", currentLocation=" + currentLocation +
                ", destinationLocation=" + destinationLocation +
                '}';
    }
}
