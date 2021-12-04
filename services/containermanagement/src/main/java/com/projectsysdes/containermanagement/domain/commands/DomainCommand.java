package com.projectsysdes.containermanagement.domain.commands;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class DomainCommand {
    private final LocalDateTime createdTime;

    public DomainCommand() {
        this.createdTime = LocalDateTime.now();
    }
}
