package be.ugent.systemdesign.towingpilotageservice.API;

import be.ugent.systemdesign.towingpilotageservice.application.command.ReserveTowingPilotageCommand;
import be.ugent.systemdesign.towingpilotageservice.application.command.ReserveTowingPilotageResponse;
import be.ugent.systemdesign.towingpilotageservice.application.command.ResponseStatus;
import be.ugent.systemdesign.towingpilotageservice.application.command.UndoReservationCommand;
import be.ugent.systemdesign.towingpilotageservice.application.event.EventHandler;
import be.ugent.systemdesign.towingpilotageservice.application.event.NavigateShipEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    private static final Logger log = LoggerFactory.getLogger(MessageInputGateway.class);

    @Autowired
    EventHandler eventHandler;

    @Autowired
    Channels channels;

    @StreamListener(Channels.RESERVE_TOWING_PILOTAGE)
    public void consumeReserveTowingPilotageCommand(ReserveTowingPilotageCommand command){
        ReserveTowingPilotageResponse response = new ReserveTowingPilotageResponse(ResponseStatus.SUCCESS, "successful towing reservation", command.getVesselId());

        channels.towingPilotageReserved().send(
                MessageBuilder
                        .withPayload(response)
                        .setHeader("spring.cloud.stream.sendto.destination", command.getResponseDestination())
                        .build()
        );
    }

    @StreamListener(Channels.UNDO_TOWING_PILOTAGE_RESERVATION)
    public void consumeUndoReservation(UndoReservationCommand command){
        log.info("Received undoreservation for vessel {}", command.getVesselId());
    }

    @StreamListener(Channels.NAVIGATE_SHIP)
    public void navigateShipEvent(NavigateShipEvent event) {
        eventHandler.navigateShipEvent(event);
    }
}
