package com.projectsysdes.containermanagement.infrastructure.container;

import com.projectsysdes.containermanagement.domain.container.ContainerLocationType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class ContainerLocationDataModel {
    private ContainerLocationType locationType;
    private Integer locationIdentifier;
}
