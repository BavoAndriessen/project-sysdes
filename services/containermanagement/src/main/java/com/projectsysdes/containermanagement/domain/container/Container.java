package com.projectsysdes.containermanagement.domain.container;


import com.projectsysdes.containermanagement.domain.events.ReadyForContainersEvent;
import com.projectsysdes.containermanagement.domain.exceptions.ContainerLocationNotProvidedException;
import com.projectsysdes.containermanagement.domain.exceptions.IllegalContainerStateChangeException;
import lombok.*;

import static com.projectsysdes.containermanagement.domain.container.ContainerState.*;

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
    @Setter
    private ReadyForContainersRequest readyForContainersRequest;

    public Container(int containerId, String contents) {
        this.containerId = containerId;
        this.contents = contents;
        this.currentLocation = new ContainerLocation(ContainerLocationType.UNKNOWN, null);
        this.state = REGISTERED;
        this.destinationLocation = new ContainerLocation(ContainerLocationType.UNKNOWN, null);
        this.destinationLocationReady = false;
    }

    // alternative methods for all previous methods to change state
    public void progressState(ContainerState newState, ContainerLocation newLocation) throws ContainerLocationNotProvidedException, IllegalContainerStateChangeException {
        if (!ContainerState.getAllowedStateChanges().get(state).contains(newState)) {
            throw new IllegalContainerStateChangeException();
        }
        state = newState;
        if (ContainerState.getRequiresNewLocation().get(newState)) {
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
