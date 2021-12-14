package com.projectsysdes.containermanagement.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContainersReadyAtDockEvent extends DomainEvent {

    private Integer berthId;

    public ContainersReadyAtDockEvent(Integer berthId) {
        super();
        this.berthId = berthId;
    }
}
