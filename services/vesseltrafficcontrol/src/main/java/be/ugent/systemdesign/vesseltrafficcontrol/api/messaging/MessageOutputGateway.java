package be.ugent.systemdesign.vesseltrafficcontrol.api.messaging;

import be.ugent.systemdesign.vesseltrafficcontrol.application.event.EventDispatcher;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.NavigateShipEvent;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.ShipArrivingEvent;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@MessagingGateway
@Service
public interface MessageOutputGateway extends EventDispatcher {

    @Gateway(requestChannel = Channels.NAVIGATE_SHIP)
    void publishNavigateShipEvent(NavigateShipEvent event);

    @Gateway(requestChannel = Channels.SHIP_ARRIVING)
    void publishShipArrivingEvent(ShipArrivingEvent event);
}
