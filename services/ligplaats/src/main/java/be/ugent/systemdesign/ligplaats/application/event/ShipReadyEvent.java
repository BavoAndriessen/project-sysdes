package be.ugent.systemdesign.ligplaats.application.event;

public class ShipReadyEvent extends DomainEvent{
    private String vesselId;
    public ShipReadyEvent(String vesselId){
        this.vesselId = vesselId;
    }
}
