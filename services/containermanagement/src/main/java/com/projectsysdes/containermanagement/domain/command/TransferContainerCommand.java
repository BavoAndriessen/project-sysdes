package com.projectsysdes.containermanagement.domain.command;

import lombok.Getter;

@Getter
public class TransferContainerCommand extends DomainCommand {
    private final Integer containerId;

    public TransferContainerCommand(Integer containerId) {
        this.containerId = containerId;
    }
}
