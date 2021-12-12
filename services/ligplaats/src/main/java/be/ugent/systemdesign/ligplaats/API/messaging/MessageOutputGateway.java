package be.ugent.systemdesign.ligplaats.API.messaging;

import be.ugent.systemdesign.ligplaats.application.event.ContainersReadyAtDockEvent;
import be.ugent.systemdesign.ligplaats.application.event.DockReadyEvent;
import be.ugent.systemdesign.ligplaats.application.event.EventDispatcher;
import be.ugent.systemdesign.ligplaats.application.event.ShipReadyEvent;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;


@MessagingGateway
@Service
public interface MessageOutputGateway extends EventDispatcher {

    @Gateway( requestChannel = Channels.DOCK_READY_EVENT)
    void sendDockReadyEvent(DockReadyEvent e);
    @Gateway(requestChannel = Channels.SHIP_READY_EVENT)
    void sendShipReadyEvent(ShipReadyEvent e);


}
