package be.ugent.systemdesign.kapiteinsdienst.application.command;

public interface CommandDispatcher {

    //void sendOfferProposalResponse(OfferProposalResponse response);

    void sendReserveBerthCommand(ReserveBerthCommand command);

    void sendReserveServiceCommand(ReserveServiceCommand command);

    void sendReserveTowingPilotageCommand(ReserveTowingPilotageCommand command);

    void sendUndoBerthReservationCommand(UndoReservationCommand command);

    void sendUndoTowingPilotageReservationCommand(UndoReservationCommand command);

    void sendUndoServiceReservationCommand(UndoReservationCommand command);

    void sendRequestOfferCommand(RequestOfferCommand command);

    void sendDeleteOfferCommand(DeleteOfferCommand command);
}
