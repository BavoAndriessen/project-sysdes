package be.ugent.systemdesign.vesseltrafficcontrol.application.event;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.NavigateShipEvent;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.ShipArrivingEvent;

public interface EventDispatcher {

    void publishNavigateShipEvent(NavigateShipEvent event);
    void publishShipArrivingEvent(ShipArrivingEvent event);

}
