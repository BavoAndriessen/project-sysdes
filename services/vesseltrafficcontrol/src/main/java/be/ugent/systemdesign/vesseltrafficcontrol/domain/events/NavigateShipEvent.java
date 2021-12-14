package be.ugent.systemdesign.vesseltrafficcontrol.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NavigateShipEvent extends DomainEvent{
    private String vesselId;
    private String path;

    public NavigateShipEvent(String vesselId, String path) {
        super();
        this.vesselId = vesselId;
        this.path = path;
    }
}
