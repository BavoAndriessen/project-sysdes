package com.projectsysdes.containermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContainerLocation {
    private ContainerLocationType locationType;
    private String locationIdentifier;

    @Override
    public String toString() {
        return "ContainerLocation{" +
                "locationType=" + locationType +
                ", locationIdentifier='" + locationIdentifier + '\'' +
                '}';
    }
}
