package be.ugent.systemdesign.kapiteinsdienst.API.messaging;

import be.ugent.systemdesign.kapiteinsdienst.application.command.*;
//import be.ugent.systemdesign.kapiteinsdienst.application.command.client.OfferProposalResponse;
import be.ugent.systemdesign.kapiteinsdienst.application.command.RequestOfferCommand;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@MessagingGateway
@Service
public interface MessageOutputGateway {

    /*
    @Gateway(requestChannel = Channels.OFFER_PROPOSAL)
    public void sendOfferProposalResponse(OfferProposalResponse response);
    */

    @Gateway(requestChannel = Channels.RESERVE_BERTH)
    public void sendReserveBerthCommand(ReserveBerthCommand command);

    @Gateway(requestChannel = Channels.RESERVE_SERVICE)
    public void sendReserveServiceCommand(ReserveServiceCommand command);

    @Gateway(requestChannel = Channels.RESERVE_TOWING_PILOTAGE)
    public void sendReserveTowingPilotageCommand(ReserveTowingPilotageCommand command);

    @Gateway(requestChannel = Channels.UNDO_BERTH_RESERVATION)
    public void sendUndoBerthReservationCommand(UndoReservationCommand command);

    @Gateway(requestChannel = Channels.UNDO_TOWING_PILOTAGE_RESERVATION)
    public void sendUndoTowingPilotageReservationCommand(UndoReservationCommand command);

    @Gateway(requestChannel = Channels.UNDO_SERVICE_RESERVATION)
    public void sendUndoServiceReservationCommand(UndoReservationCommand command);

    @Gateway(requestChannel = Channels.REQUEST_OFFER)
    public void sendRequestOfferCommand(RequestOfferCommand command);

    @Gateway(requestChannel = Channels.DELETE_OFFER)
    public void sendDeleteOfferCommand(DeleteOfferCommand command);
}
