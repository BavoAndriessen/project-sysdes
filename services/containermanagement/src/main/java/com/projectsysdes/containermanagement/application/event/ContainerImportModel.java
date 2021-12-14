package com.projectsysdes.containermanagement.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ContainerImportModel {
    private Integer containerId;
    private String contents;
}
