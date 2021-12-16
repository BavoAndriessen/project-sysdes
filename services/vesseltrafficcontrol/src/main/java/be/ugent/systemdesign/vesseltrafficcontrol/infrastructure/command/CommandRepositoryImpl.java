package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.command;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.ChangeGateStateCommand;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.CommandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandRepositoryImpl implements CommandRepository {

    @Autowired
    CommandDataModelRepository<ChangeGateStateCommandDataModel> repository;

    Logger log = LoggerFactory.getLogger(CommandRepositoryImpl.class);

    @Override
    public List<ChangeGateStateCommand> findAllGateCommands() {
        return repository.findAll().stream().map(this::toChangeGateStateCommand).collect(Collectors.toList());
    }

    @Override
    public void save(ChangeGateStateCommand changeGateStateCommandDataModel) {
        repository.save(toChangeGateStateCommandDataModel(changeGateStateCommandDataModel));
    }

    @Override
    public void remove(Integer gateId) {
        log.warn("gate id: " + gateId);
        Optional<ChangeGateStateCommandDataModel> command = repository.findAll().stream().filter(cmd -> cmd.getGateId().equals(gateId)).findFirst();
        if(command.isPresent()) {
            repository.delete(command.get());
            log.warn("command is present with id: " + command.get().getGateId());
        }
    }

    private ChangeGateStateCommand toChangeGateStateCommand(ChangeGateStateCommandDataModel commandDM) {
        return new ChangeGateStateCommand(commandDM.getGateId());
    }

    private ChangeGateStateCommandDataModel toChangeGateStateCommandDataModel(ChangeGateStateCommand command){
        return new ChangeGateStateCommandDataModel(command.getGateId());
    }

}
