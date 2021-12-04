package com.projectsysdes.containermanagement.domain.commands;

import com.projectsysdes.containermanagement.domain.ContainerLocation;
import lombok.Getter;

import java.util.List;
@Getter
public class TransferContainersCommand extends DomainCommand {
    private final List<Integer> containerIds;
    private final ContainerLocation toLocation;

    public TransferContainersCommand(List<Integer> containerIds, ContainerLocation to) {
        this.containerIds = containerIds;
        this.toLocation = to;
    }
}
