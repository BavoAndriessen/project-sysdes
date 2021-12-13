package be.ugent.systemdesign.vesseltrafficcontrol.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArrivedEvent extends DomainEvent{
    private String vesslId;

    public ArrivedEvent(String vesslId) {
        super();
        this.vesslId = vesslId;
    }
}
