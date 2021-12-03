package com.projectsysdes.containermanagement.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public abstract class DomainEvent {
    private LocalDateTime createdTime;
}
