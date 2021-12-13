package be.ugent.systemdesign.vesseltrafficcontrol.domain.command;

import lombok.Getter;

@Getter
public class ChangeGateStateCommand extends DomainCommand{
    private final Integer gateId;

    public ChangeGateStateCommand(Integer gateId) {
        this.gateId = gateId;
    }
}
