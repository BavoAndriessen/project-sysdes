package be.ugent.systemdesign.kapiteinsdienst.API;

import be.ugent.systemdesign.kapiteinsdienst.application.command.*;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.AcceptOfferCommand;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.CreateOfferCommand;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.RefuseOfferCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    @Autowired
    CommandHandler commandHandler;

    //Scheepsagent
    @StreamListener(Channels.CREATE_OFFER)
    public void consumeCreateOfferCommand(CreateOfferCommand command){
        commandHandler.handleCreateOfferCommand(command);
    }
    //Scheepsagent
    @StreamListener(Channels.ACCEPT_OFFER)
    public void consumeAcceptOfferCommand(AcceptOfferCommand command){
        commandHandler.handleAcceptOfferCommand(command);
    }

    //Scheepsagent
    @StreamListener(Channels.REFUSE_OFFER)
    public void consumeRefuseOfferCommand(RefuseOfferCommand command){
        commandHandler.handleRefuseOfferCommand(command);
    }

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

    //Administratie
    @StreamListener(Channels.OFFER_CREATED)
    public void consumeOfferCreatedResponse(OfferCreatedResponse response) {
        commandHandler.handleOfferCreatedResponse(response);
    }
}
