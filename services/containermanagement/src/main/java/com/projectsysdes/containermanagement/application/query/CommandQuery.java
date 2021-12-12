package com.projectsysdes.containermanagement.application.query;

import com.projectsysdes.containermanagement.domain.command.TransferContainerCommand;

import java.util.List;

public interface CommandQuery {
    List<TransferContainerCommand> findAllTransferContainerCommands();
    void saveTransferContainerCommand(TransferContainerCommand tcc);
}
