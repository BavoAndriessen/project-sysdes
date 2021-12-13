package be.ugent.systemdesign.ligplaats.application.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipArrivingEvent extends DomainEvent {
    private String vesselId;

    public ShipArrivingEvent(String vesselId){
        super();
        this.vesselId = vesselId;

    }
}
