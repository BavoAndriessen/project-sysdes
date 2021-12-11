package com.projectsysdes.containermanagement.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContainerImportModel {
    private final Integer containerId;
    private final String contents;
}
