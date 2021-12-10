package com.projectsysdes.containermanagement.application.query;

import com.projectsysdes.containermanagement.API.REST.ContainerViewModel;
import com.projectsysdes.containermanagement.domain.ContainerLocation;
import com.projectsysdes.containermanagement.domain.commands.TransferContainersCommand;

import java.util.List;

public interface ContainerQuery {
    ContainerLocation getContainerLocation(Integer containerId);
    List<ContainerViewModel> searchContainersForContents(String contents);
    List<TransferContainersCommand> findAllTransferCommands();
    void save(TransferContainersCommand transferContainersCommand);
}
