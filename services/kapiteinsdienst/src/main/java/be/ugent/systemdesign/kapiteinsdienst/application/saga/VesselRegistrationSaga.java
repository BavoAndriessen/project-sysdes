package be.ugent.systemdesign.kapiteinsdienst.application.saga;

import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import be.ugent.systemdesign.kapiteinsdienst.application.command.*;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.OfferProposalResponse;
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
        vessel.setStatus(VesselStatus.OFFER_REQUESTED);
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
        vessel.setStatus(VesselStatus.RESERVATION_FAIL);
        vesselRepo.save(vessel);

        UndoReservationCommand undoReservationCommand = new UndoReservationCommand(vessel.getVesselNumber());
        commandDispatcher.sendUndoBerthReservationCommand(undoReservationCommand);
        commandDispatcher.sendUndoServiceReservationCommand(undoReservationCommand);
        commandDispatcher.sendUndoTowingPilotageReservationCommand(undoReservationCommand);

        //TODO is offerId wel beschikbaar? kan dit met enkel vesselnumber?
        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(vessel.getVesselNumber(), vessel.getOfferId());
        commandDispatcher.sendDeleteOfferCommand(deleteOfferCommand);

        OfferProposalResponse offerProposalResponse = new OfferProposalResponse(ResponseStatus.FAIL,"",vessel.getVesselNumber(),vessel.getPrice(),vessel.getOfferId());
        commandDispatcher.sendOfferProposalResponse(offerProposalResponse);

    }

    public void onOfferCreatedByAdministration(Vessel vessel, Integer offerId, Double price){
        vessel.setOfferId(offerId);
        vessel.setPrice(price);
        vesselRepo.save(vessel);

    }
    //TODO oproepen(*) bij methodes in saga die worden aangeroepen wanneer reservatie lukt; binnen die oproep(*) controleren of andere reservaties gelukt zien, indien ja dan onRegistrationComplete oproepen
    //check of offerId aanwezig is
    public void onRegistrationComplete(Vessel vessel){
        vessel.setStatus(VesselStatus.OFFER_CREATED);
        vesselRepo.save(vessel);

        OfferProposalResponse offerProposalResponse = new OfferProposalResponse(ResponseStatus.SUCCESS,"",vessel.getVesselNumber(),vessel.getPrice(),vessel.getOfferId());
        commandDispatcher.sendOfferProposalResponse(offerProposalResponse);
    }




}
