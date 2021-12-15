package be.ugent.systemdesign.ligplaats.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipReadyEvent extends DomainEvent{
    private String vesselId;
    private int berthNumber;
    public ShipReadyEvent(String vesselId, int berthNumber){

        this.vesselId = vesselId;
        this.berthNumber  = berthNumber;
    }
}
