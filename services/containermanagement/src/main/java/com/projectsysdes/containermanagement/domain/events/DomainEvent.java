package com.projectsysdes.containermanagement.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class DomainEvent {
    private final LocalDateTime createdTime;

    public DomainEvent() {
        this.createdTime = LocalDateTime.now();
    }
}
