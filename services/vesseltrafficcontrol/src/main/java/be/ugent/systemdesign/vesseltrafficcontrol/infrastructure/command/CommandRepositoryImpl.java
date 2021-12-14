package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.command;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.ChangeGateStateCommand;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandRepositoryImpl implements CommandRepository {

    @Autowired
    CommandDataModelRepository<ChangeGateStateCommandDataModel> repository;

    @Override
    public List<ChangeGateStateCommand> findAllGateCommands() {
        return repository.findAll().stream().map(this::toChangeGateStateCommand).collect(Collectors.toList());
    }

    @Override
    public void save(ChangeGateStateCommand changeGateStateCommandDataModel) {
        repository.save(toChangeGateStateCommandDataModel(changeGateStateCommandDataModel));
    }

    private ChangeGateStateCommand toChangeGateStateCommand(ChangeGateStateCommandDataModel commandDM) {
        return new ChangeGateStateCommand(commandDM.getGateId());
    }

    private ChangeGateStateCommandDataModel toChangeGateStateCommandDataModel(ChangeGateStateCommand command){
        return new ChangeGateStateCommandDataModel(command.getGateId());
    }

}
