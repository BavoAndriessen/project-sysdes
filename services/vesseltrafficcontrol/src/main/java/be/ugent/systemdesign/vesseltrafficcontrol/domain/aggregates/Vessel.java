package be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import lombok.Getter;

@Getter
public class Vessel {
    private final String vesselId;
    private final Size size;

    public Vessel(String id, Size size){
        this.vesselId = id;
        this.size = size;
    }
}
