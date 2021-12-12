package com.projectsysdes.containermanagement.domain.command;

import java.util.List;

public interface CommandRepository {
    List<TransferContainerCommand> findAllTransferCommands();
    void  save(TransferContainerCommand transferContainerCommandDataModel);
}
