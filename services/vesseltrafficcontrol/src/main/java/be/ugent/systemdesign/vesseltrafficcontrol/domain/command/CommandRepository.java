package be.ugent.systemdesign.vesseltrafficcontrol.domain.command;

import java.util.List;

public interface CommandRepository {
    List<ChangeGateStateCommand> findAllGateCommands();
    boolean findById(Integer id);
    void save(ChangeGateStateCommand changeGateStateCommandDataModel);
    void remove(Integer gateId);
}
