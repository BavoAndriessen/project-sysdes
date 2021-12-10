package com.projectsysdes.containermanagement.infrastructure;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.projectsysdes.containermanagement.domain.ContainerLocation;
import com.projectsysdes.containermanagement.domain.ContainerLocationType;
import com.projectsysdes.containermanagement.domain.ContainerState;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class ContainerDataModel {
    @Id
//    @GeneratedValue
    private Integer containerId;
    private String contents;
    private String state;
    private String currentLocationType;
    private String currentLocationIdentifier;
    private String destinationLocationType;
    private String destinationLocationIdentifier;
    private boolean destinationLocationReady;

    public ContainerDataModel(int containerId,String contents, ContainerLocation destinationLocation) {
        this.containerId = containerId;
        this.contents = contents;
        this.currentLocationType = ContainerLocationType.UNKNOWN.name();
        this.currentLocationIdentifier = "";
        this.state = ContainerState.REGISTERED.name();
        this.destinationLocationType = destinationLocation.getLocationType().name();
        this.destinationLocationIdentifier = destinationLocation.getLocationIdentifier();
    }

}
