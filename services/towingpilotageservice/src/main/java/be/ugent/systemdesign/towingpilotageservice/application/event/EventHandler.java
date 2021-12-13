package be.ugent.systemdesign.towingpilotageservice.application.event;

import be.ugent.systemdesign.towingpilotageservice.API.MessageOutputGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EventHandler {
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    @Autowired
    MessageOutputGateway outputGateway;

    public void navigateShipEvent(NavigateShipEvent event) {
        log.info("NavigateShipEvent for vessel {} received by towingpilotageservice", event.getVesselId());
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outputGateway.publishArrivedEvent(new ArrivedEvent(event.getVesselId()));


    }
}
