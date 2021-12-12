package com.projectsysdes.containermanagement.infrastructure.command;

import com.projectsysdes.containermanagement.application.query.CommandQuery;
import com.projectsysdes.containermanagement.domain.command.CommandRepository;
import com.projectsysdes.containermanagement.domain.command.TransferContainerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandQueryImpl implements CommandQuery {

    @Autowired
    CommandRepository repo;

    @Override
    public List<TransferContainerCommand> findAllTransferContainerCommands() {
        return repo.findAllTransferCommands();
    }

    @Override
    public void saveTransferContainerCommand(TransferContainerCommand tcc) {
        repo.save(tcc);
    }
}
