package com.projectsysdes.containermanagement.domain.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
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
