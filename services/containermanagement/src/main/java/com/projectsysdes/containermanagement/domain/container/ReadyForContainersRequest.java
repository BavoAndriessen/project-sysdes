package com.projectsysdes.containermanagement.domain.container;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadyForContainersRequest {
    private Integer id;

    public ReadyForContainersRequest() {
        id=null;
    }
}
