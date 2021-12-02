package be.ugent.systemdesign.kapiteinsdienst.API;

import be.ugent.systemdesign.kapiteinsdienst.application.command.*;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.OfferProposalResponse;
import be.ugent.systemdesign.kapiteinsdienst.application.command.RequestOfferCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommandDispatcherImpl implements CommandDispatcher {

    @Autowired
    MessageOutputGateway outputGateway;

    @Value("${spring.cloud.stream.bindings." + Channels.BERTH_RESERVED + ".destination}")
    String reserveBerthResponseDestination;

    @Value("${spring.cloud.stream.bindings." + Channels.SERVICE_RESERVED + ".destination}")
    String reserveServiceResponseDestination;

    @Value("${spring.cloud.stream.bindings." + Channels.TOWING_PILOTAGE_RESERVED + ".destination}")
    String reserveTowingPilotageResponseDestination;

    @Value("${spring.cloud.stream.bindings." + Channels.REQUEST_OFFER + ".destination}")
    String requestOfferAdministrationDestination;

    @Override
    public void sendOfferProposalResponse(OfferProposalResponse response) {
        outputGateway.sendOfferProposalResponse(response);
    }

    @Override
    public void sendReserveBerthCommand(ReserveBerthCommand command) {
        outputGateway.sendReserveBerthCommand(
                new ReserveBerthCommand(command.getVesselNumber(),command.getVesselSize(),command.getArrivalDateTime(),command.getDepartureDateTime(), reserveBerthResponseDestination)
        );
    }

    @Override
    public void sendReserveServiceCommand(ReserveServiceCommand command) {
        outputGateway.sendReserveServiceCommand(
                new ReserveServiceCommand(command.getVesselNumber(),command.getArrivalDateTime(),command.getDepartureDateTime(),command.getAdditionalServices(),reserveServiceResponseDestination)
        );
    }

    @Override
    public void sendReserveTowingPilotageCommand(ReserveTowingPilotageCommand command) {
        outputGateway.sendReserveTowingPilotageCommand(
                new ReserveTowingPilotageCommand(command.getVesselNumber(),command.getArrivalDateTime(),command.getDepartureDateTime(),reserveTowingPilotageResponseDestination)
        );
    }

    @Override
    public void sendRequestOfferCommand(RequestOfferCommand command) {
        outputGateway.sendRequestOfferCommand(
                new RequestOfferCommand(command.getVesselNumber(),command.getArrivalDateTime(),command.getDepartureDateTime(),command.getVesselSize(),command.getAmountOfWaste(),command.getContainerList(),command.getCrewList(),requestOfferAdministrationDestination)
        );
    }

    @Override
    public void sendUndoBerthReservationCommand(UndoReservationCommand command) {
        outputGateway.sendUndoBerthReservationCommand(command);
    }

    @Override
    public void sendUndoTowingPilotageReservationCommand(UndoReservationCommand command) {
        outputGateway.sendUndoTowingPilotageReservationCommand(command);
    }

    @Override
    public void sendUndoServiceReservationCommand(UndoReservationCommand command) {
        outputGateway.sendUndoServiceReservationCommand(command);
    }

    @Override
    public void sendDeleteOfferCommand(DeleteOfferCommand command) {
        outputGateway.sendDeleteOfferCommand(command);
    }
}
