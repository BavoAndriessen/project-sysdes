package be.ugent.systemdesign.vesseltrafficcontrol.application.query;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.ChangeGateStateCommand;

import java.util.List;

public interface CommandQuery {
    List<ChangeGateStateCommand> findAllChangeGateStateCommands();
    void deleteCommand(Integer gateId);
}
