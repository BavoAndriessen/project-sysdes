package be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import lombok.Getter;

@Getter
public class Vessel {
    private final Integer vesselId;
    private final Size size;

    public Vessel(Integer id, Size size){
        this.vesselId = id;
        this.size = size;
    }
}
