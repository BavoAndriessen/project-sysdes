package be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateState;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateType;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Gate {

    private final Integer gateId;
    private @Setter
    GateType type;
    private @Setter
    GateState state;
    private final Size sizeCompatibility;

    public Gate(Integer _gateId, GateType _type, GateState _state, Size size){
        gateId = _gateId;
        type = _type;
        state = _state;
        sizeCompatibility = size;
    }
}
