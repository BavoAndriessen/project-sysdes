package be.ugent.systemdesign.administrationservice.API.messaging;

import be.ugent.systemdesign.administrationservice.application.command.CommandHandler;
import be.ugent.systemdesign.administrationservice.application.command.DeleteOfferCommand;
import be.ugent.systemdesign.administrationservice.application.command.OfferCreatedResponse;
import be.ugent.systemdesign.administrationservice.application.command.RequestOfferCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    @Autowired
    Channels channels;

    @Autowired
    CommandHandler commandHandler;

    @StreamListener(Channels.REQUEST_OFFER)
    @SendTo(Channels.OFFER_CREATED)
    public OfferCreatedResponse receiveRequestOfferCommand(RequestOfferCommand command){
        return commandHandler.requestNewOfferForVessel(command);
    }

    @StreamListener(Channels.DELETE_OFFER)
    public void receiveDeleteOfferCommand(DeleteOfferCommand command){
        commandHandler.requestToDeleteOffer(command);
    }
}
