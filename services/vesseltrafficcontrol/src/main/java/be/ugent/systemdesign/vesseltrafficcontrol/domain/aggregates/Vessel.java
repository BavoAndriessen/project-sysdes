package be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.VesselState;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Vessel {
    private final String vesselId;
    private final Size size;
    @Setter
    private VesselState state;

    public Vessel(String id, Size size){
        this.vesselId = id;
        this.size = size;
        state = VesselState.MOVING;
    }

    public String toString() {
        return "id: " + vesselId + ", size: " + size;
    }
}
