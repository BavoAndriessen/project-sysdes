package be.ugent.systemdesign.vesseltrafficcontrol.api.messaging;

import be.ugent.systemdesign.vesseltrafficcontrol.application.event.EventHandler;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.ArrivedEvent;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.DockReadyEvent;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.ShipReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;



@Component
public class MessageInputGateway {

    @Autowired
    EventHandler eventHandler;

    @StreamListener(Channels.ARRIVED)
    void consumeArrivedEvent(ArrivedEvent event) {
        eventHandler.consumeArrivedEvent(event);
    }

    @StreamListener(Channels.DOCK_READY)
    void consumeDockReadyEvent(DockReadyEvent event) {
        eventHandler.consumeDockReadyEvent(event);
    }

    @StreamListener(Channels.SHIP_READY)
    void consumeShipReadyEvent(ShipReadyEvent event) {
        eventHandler.consumeShipReadyEvent(event);
    }
}
