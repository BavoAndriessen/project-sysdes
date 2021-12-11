package be.ugent.systemdesign.kapiteinsdienst.API.messaging;

import be.ugent.systemdesign.kapiteinsdienst.application.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    @Autowired
    CommandHandler commandHandler;

    @StreamListener(Channels.BERTH_RESERVED)
    public void consumeReserveBerthResponse(ReserveBerthResponse response) {
        commandHandler.handleReserveBerthResponse(response);
    }

    @StreamListener(Channels.SERVICE_RESERVED)
    public void consumeReserveServiceResponse(ReserveServiceResponse response) {
        commandHandler.handleReserveServiceResponse(response);
    }

    @StreamListener(Channels.TOWING_PILOTAGE_RESERVED)
    public void consumeReserveTowingPilotageResponse(ReserveTowingPilotageResponse response) {
        commandHandler.handleReserveTowingPilotageResponse(response);
    }

    @StreamListener(Channels.OFFER_CREATED)
    public void consumeOfferCreatedResponse(OfferCreatedResponse response) {
        commandHandler.handleOfferCreatedResponse(response);
    }
}
