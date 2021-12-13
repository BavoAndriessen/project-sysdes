package be.ugent.systemdesign.vesseltrafficcontrol.domain.command;

import java.util.List;

public interface CommandRepository {
    List<ChangeGateStateCommand> findAllGateCommands();
    void save(ChangeGateStateCommand changeGateStateCommandDataModel);
}
