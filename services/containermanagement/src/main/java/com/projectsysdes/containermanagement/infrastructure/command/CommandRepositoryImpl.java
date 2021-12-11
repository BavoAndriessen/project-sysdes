package com.projectsysdes.containermanagement.infrastructure.command;

import com.projectsysdes.containermanagement.domain.command.CommandRepository;
import com.projectsysdes.containermanagement.domain.command.TransferContainerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandRepositoryImpl implements CommandRepository {

    @Autowired
    CommandDataModelRepository<TransferContainerCommandDataModel> repository;

    @Override
    public List<TransferContainerCommand> findAllTransferCommands() {
        return repository.findAll().stream().map(this::toTransferContainerCommand).collect(Collectors.toList());
    }

    @Override
    public void save(TransferContainerCommand transferContainerCommandDataModel) {
        repository.save(toTransferContainersCommandDataModel(transferContainerCommandDataModel));
    }


    private TransferContainerCommand toTransferContainerCommand(TransferContainerCommandDataModel tccdm) {
        return new TransferContainerCommand(tccdm.getContainerId());
    }

    private TransferContainerCommandDataModel toTransferContainersCommandDataModel(TransferContainerCommand tcc) {
        return new TransferContainerCommandDataModel(tcc.getContainerId());
    }

}
