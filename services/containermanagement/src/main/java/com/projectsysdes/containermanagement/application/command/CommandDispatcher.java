package com.projectsysdes.containermanagement.application.command;

import com.projectsysdes.containermanagement.domain.commands.TransferContainersCommand;


public interface CommandDispatcher {
    void transferContainersCommand(TransferContainersCommand transferContainersCommand);

}
