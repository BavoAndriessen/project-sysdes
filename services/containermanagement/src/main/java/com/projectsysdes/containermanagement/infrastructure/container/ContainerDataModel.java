package com.projectsysdes.containermanagement.infrastructure.container;

import com.projectsysdes.containermanagement.domain.container.ContainerLocation;
import com.projectsysdes.containermanagement.domain.container.ContainerLocationType;
import com.projectsysdes.containermanagement.domain.container.ContainerState;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class ContainerDataModel {
    @Id
    private Integer containerId;
    private String contents;
    private String state;
    private String currentLocationType;
    private Integer currentLocationIdentifier;
    private String destinationLocationType;
    private Integer destinationLocationIdentifier;
    private boolean destinationLocationReady;

    @ManyToOne(cascade = {CascadeType.ALL})
    private ReadyForContainersRequestDataModel readyForContainersRequestDataModel;

    public ContainerDataModel(int containerId,String contents, ContainerLocation destinationLocation) {
        this.containerId = containerId;
        this.contents = contents;
        this.currentLocationType = ContainerLocationType.UNKNOWN.name();
        this.currentLocationIdentifier = null;
        this.state = ContainerState.REGISTERED.name();
        this.destinationLocationType = destinationLocation.getLocationType().name();
        this.destinationLocationIdentifier = destinationLocation.getLocationIdentifier();
        this.readyForContainersRequestDataModel = null;
    }

}
