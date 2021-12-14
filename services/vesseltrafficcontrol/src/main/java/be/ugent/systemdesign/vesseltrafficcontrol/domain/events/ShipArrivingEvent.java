package be.ugent.systemdesign.vesseltrafficcontrol.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipArrivingEvent extends DomainEvent{
    private String vesselId;
    private int dockNumber;

    public ShipArrivingEvent(String vesselId, int dockNumber) {
        super();
        this.vesselId = vesselId;
        this.dockNumber = dockNumber;
    }
}
