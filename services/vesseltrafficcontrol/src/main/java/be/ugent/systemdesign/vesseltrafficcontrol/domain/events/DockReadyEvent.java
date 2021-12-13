package be.ugent.systemdesign.vesseltrafficcontrol.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DockReadyEvent extends DomainEvent{
    private int dockNumber;

    public DockReadyEvent(int dock) {
        super();
        this.dockNumber = dock;
    }
}
