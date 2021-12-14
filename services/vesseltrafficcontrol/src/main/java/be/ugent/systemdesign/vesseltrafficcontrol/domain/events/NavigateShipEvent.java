package be.ugent.systemdesign.vesseltrafficcontrol.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NavigateShipEvent extends DomainEvent{
    private String vesselId;
    private int dockNumber;

    public NavigateShipEvent(String vesselId, int dockNumber) {
        super();
        this.vesselId = vesselId;
        this.dockNumber = dockNumber;
    }
}
