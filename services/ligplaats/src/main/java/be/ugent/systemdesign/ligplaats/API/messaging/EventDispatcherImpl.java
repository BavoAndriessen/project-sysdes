package be.ugent.systemdesign.ligplaats.API.messaging;

import be.ugent.systemdesign.ligplaats.application.event.DockReadyEvent;
import be.ugent.systemdesign.ligplaats.application.event.EventDispatcher;
import be.ugent.systemdesign.ligplaats.application.event.ShipReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcherImpl implements EventDispatcher {

    @Autowired
    MessageOutputGateway outputGateway;


    @Override
    public void sendDockReadyEvent(DockReadyEvent e) {
        outputGateway.sendDockReadyEvent(e);
    }

    @Override
    public void sendShipReadyEvent(ShipReadyEvent e) {
        outputGateway.sendShipReadyEvent(e);
    }
}
