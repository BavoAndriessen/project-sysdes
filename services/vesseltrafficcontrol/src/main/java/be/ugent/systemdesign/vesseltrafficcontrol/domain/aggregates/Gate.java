package be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateState;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateType;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Gate {

    private final int gateId;
    private @Setter
    GateType type;
    private @Setter
    GateState state;
    private final Size sizeCompatibility;

    public Gate(int _gateId, GateType _type, GateState _state, Size size){
        gateId = _gateId;
        type = _type;
        state = _state;
        sizeCompatibility = size;
    }

    public void toggleState(){
        if(state == GateState.OPEN)
            state = GateState.CLOSE;
        else
            state = GateState.OPEN;
    }
}
