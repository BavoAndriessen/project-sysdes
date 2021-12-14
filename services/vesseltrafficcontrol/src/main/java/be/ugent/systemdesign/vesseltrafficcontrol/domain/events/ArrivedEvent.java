package be.ugent.systemdesign.vesseltrafficcontrol.domain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArrivedEvent extends DomainEvent{
    private String vesslId;
    private Integer destination;

    public ArrivedEvent(String vesslId, Integer destination) {
        super();
        this.vesslId = vesslId;
        this.destination = destination;
    }
}
