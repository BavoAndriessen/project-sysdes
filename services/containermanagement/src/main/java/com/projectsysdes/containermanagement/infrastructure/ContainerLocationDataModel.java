package com.projectsysdes.containermanagement.infrastructure;

import com.projectsysdes.containermanagement.domain.ContainerLocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class ContainerLocationDataModel {
    private ContainerLocationType locationType;
    private String locationIdentifier;
}
