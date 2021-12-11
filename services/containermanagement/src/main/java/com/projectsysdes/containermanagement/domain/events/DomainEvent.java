package com.projectsysdes.containermanagement.domain.events;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DomainEvent {
    private LocalDateTime createdTime;

    public DomainEvent() {
        this.createdTime = LocalDateTime.now();
    }
}
