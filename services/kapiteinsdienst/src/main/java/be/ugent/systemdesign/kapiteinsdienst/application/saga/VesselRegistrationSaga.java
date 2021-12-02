package be.ugent.systemdesign.kapiteinsdienst.application.saga;

import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import be.ugent.systemdesign.kapiteinsdienst.application.command.*;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.OfferProposalResponse;
import be.ugent.systemdesign.kapiteinsdienst.domain.ReservationServices;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class VesselRegistrationSaga {

    @Autowired
    VesselRepository vesselRepo;

    @Autowired
    CommandDispatcher commandDispatcher;

    public void startVesselRegistration(Vessel vessel){
        vessel.newRegistration();
        vesselRepo.save(vessel);

        ReserveBerthCommand reserveBerthCommand = new ReserveBerthCommand(vessel.getVesselNumber(),vessel.getVesselSize(),vessel.getArrivalDateTime(),vessel.getDepartureDateTime());
        commandDispatcher.sendReserveBerthCommand(reserveBerthCommand);

        ReserveServiceCommand reserveServiceCommand = new ReserveServiceCommand(vessel.getVesselNumber(),vessel.getArrivalDateTime(), vessel.getDepartureDateTime(),vessel.getAdditionalServices());
        commandDispatcher.sendReserveServiceCommand(reserveServiceCommand);

        ReserveTowingPilotageCommand towingPilotageCommand = new ReserveTowingPilotageCommand(vessel.getVesselNumber(),vessel.getArrivalDateTime(),vessel.getDepartureDateTime());
        commandDispatcher.sendReserveTowingPilotageCommand(towingPilotageCommand);

        RequestOfferCommand requestOfferCommand = new RequestOfferCommand(
                vessel.getVesselNumber(),
                vessel.getArrivalDateTime(),
                vessel.getDepartureDateTime(),
                vessel.getVesselSize(),
                vessel.getAmountOfWaste(),
                vessel.getContainerList(),
                vessel.getCrewList()
        );
        commandDispatcher.sendRequestOfferCommand(requestOfferCommand);
    }

    public void onReservationFail(Vessel vessel){
        vessel.failedReservation();
        vesselRepo.save(vessel);

        sendUndoReservationToAllServices(vessel.getVesselNumber());

        //TODO is offerId wel beschikbaar? kan dit met enkel vesselnumber?
        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(vessel.getVesselNumber(), vessel.getOfferId());
        commandDispatcher.sendDeleteOfferCommand(deleteOfferCommand);

        OfferProposalResponse offerProposalResponse = new OfferProposalResponse(ResponseStatus.FAIL,"",vessel.getVesselNumber(),vessel.getPrice(),vessel.getOfferId());
        commandDispatcher.sendOfferProposalResponse(offerProposalResponse);

    }

    public void onReservationSuccess(Vessel vessel, ReservationServices service){
        vessel.updateReservationStatus(service);
        vesselRepo.save(vessel);
        if(vessel.checkOfferAvailability()){
            onRegistrationComplete(vessel);
        }
    }

    public void onOfferCreatedByAdministration(Vessel vessel, Integer offerId, Double price){
        vessel.updateOfferInfo(offerId, price);
        vesselRepo.save(vessel);
        if(vessel.checkOfferAvailability()){
            onRegistrationComplete(vessel);
        }

    }

    public void onOfferConfirmation(Vessel vessel, Boolean isConfirmed){
        vessel.offerConfirmation(isConfirmed);
        vesselRepo.save(vessel);

        if(!isConfirmed){
            sendUndoReservationToAllServices(vessel.getVesselNumber());
        }
    }

    private void onRegistrationComplete(Vessel vessel){
        OfferProposalResponse offerProposalResponse = new OfferProposalResponse(ResponseStatus.SUCCESS,"",vessel.getVesselNumber(),vessel.getPrice(),vessel.getOfferId());
        commandDispatcher.sendOfferProposalResponse(offerProposalResponse);
    }

    private void sendUndoReservationToAllServices(Integer vesselNumber){
        UndoReservationCommand undoReservationCommand = new UndoReservationCommand(vesselNumber);
        commandDispatcher.sendUndoBerthReservationCommand(undoReservationCommand);
        commandDispatcher.sendUndoServiceReservationCommand(undoReservationCommand);
        commandDispatcher.sendUndoTowingPilotageReservationCommand(undoReservationCommand);
    }



}
