package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.command;

import be.ugent.systemdesign.vesseltrafficcontrol.application.query.CommandQuery;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.ChangeGateStateCommand;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandQueryImpl implements CommandQuery {

    @Autowired
    CommandRepository repository;

    @Override
    public List<ChangeGateStateCommand> findAllChangeGateStateCommands() {
        return repository.findAllGateCommands();
    }

    @Override
    public void deleteCommand(Integer gateId) {
        repository.remove(gateId);
    }

}
