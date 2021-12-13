package com.projectsysdes.containermanagement.domain.container;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContainerLocation {
    private ContainerLocationType locationType;
    private Integer locationIdentifier;

    @Override
    public String toString() {
        return "ContainerLocation{" +
                "locationType=" + locationType +
                ", locationIdentifier='" + locationIdentifier + '\'' +
                '}';
    }
}
