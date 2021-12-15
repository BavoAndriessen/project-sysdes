package be.ugent.systemdesign.ligplaats.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipArrivingEvent extends DomainEvent {
    private String vesselId;

    public ShipArrivingEvent(String vesselId){
        super();
        this.vesselId = vesselId;

    }
}
