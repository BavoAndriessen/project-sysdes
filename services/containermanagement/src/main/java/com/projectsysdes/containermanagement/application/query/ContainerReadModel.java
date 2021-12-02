package com.projectsysdes.containermanagement.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContainerReadModel {
    private String locationType;
    private String locationIdentifier;
}
