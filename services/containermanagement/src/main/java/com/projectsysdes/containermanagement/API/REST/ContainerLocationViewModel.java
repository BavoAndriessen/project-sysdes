package com.projectsysdes.containermanagement.API.REST;

import com.projectsysdes.containermanagement.domain.ContainerLocationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContainerLocationViewModel {
    private String locationType;
    private String locationIdentifier;
}
