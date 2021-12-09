package be.ugent.systemdesign.towingpilotageservice.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {
    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    public void handleArrivalTimeChangedEvent(ArrivalTimeChangedEvent event) {
        log.info("ArrivalTimeChangedEvent for vessel {} received by towingpilotageservice", event.getVesselId());
    }
}
