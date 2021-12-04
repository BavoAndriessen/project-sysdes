package com.projectsysdes.containermanagement.domain.commands;

import lombok.Getter;

import java.util.List;
@Getter
public class TransferContainersCommand extends DomainCommand {
    private final List<Integer> containerIds;

    public TransferContainersCommand(List<Integer> containerIds) {
        this.containerIds = containerIds;
    }
}
