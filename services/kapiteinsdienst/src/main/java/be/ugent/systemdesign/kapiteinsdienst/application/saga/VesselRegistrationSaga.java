package be.ugent.systemdesign.kapiteinsdienst.application.saga;

import be.ugent.systemdesign.kapiteinsdienst.application.command.*;
import be.ugent.systemdesign.kapiteinsdienst.domain.ReservationServices;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
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
        vesselRepo.saveAndFlush(vessel);

        ReserveBerthCommand reserveBerthCommand = new ReserveBerthCommand(vessel.getVesselId(),vessel.getVesselSize(),vessel.getArrivalDateTime(),vessel.getLengthOfStay());
        commandDispatcher.sendReserveBerthCommand(reserveBerthCommand);

        ReserveServiceCommand reserveServiceCommand = new ReserveServiceCommand(vessel.getVesselId(),vessel.getArrivalDateTime(), vessel.getLengthOfStay(),vessel.getAdditionalServices());
        commandDispatcher.sendReserveServiceCommand(reserveServiceCommand);

        ReserveTowingPilotageCommand towingPilotageCommand = new ReserveTowingPilotageCommand(vessel.getVesselId(),vessel.getArrivalDateTime(),vessel.getLengthOfStay());
        commandDispatcher.sendReserveTowingPilotageCommand(towingPilotageCommand);

        RequestOfferCommand requestOfferCommand = new RequestOfferCommand(
                vessel.getVesselId(),
                vessel.getArrivalDateTime(),
                vessel.getLengthOfStay(),
                vessel.getVesselSize(),
                vessel.getAmountOfWaste(),
                vessel.getContainerList()
        );
        commandDispatcher.sendRequestOfferCommand(requestOfferCommand);
    }

    public void onReservationFail(String vesselId){
        Vessel vessel = vesselRepo.findById(vesselId);
        vessel.failedReservation();
        vesselRepo.saveAndFlush(vessel);
        sendUndoReservationToAllServices(vessel.getVesselId());
        //TODO is offerId wel beschikbaar? kan dit met enkel vesselId?
        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(vessel.getVesselId(), vessel.getOfferId());
        commandDispatcher.sendDeleteOfferCommand(deleteOfferCommand);
    }

    public void onReservationSuccess(String vesselId, ReservationServices service){
        Vessel vessel = vesselRepo.findById(vesselId);
        vessel.updateReservationStatus(service);
        vesselRepo.saveAndFlush(vessel);
    }

    public void onOfferCreatedByAdministration(String vesselId, Integer offerId, Double price){
        Vessel vessel = vesselRepo.findById(vesselId);
        vessel.updateOfferInfo(offerId, price);
        vesselRepo.saveAndFlush(vessel);
    }

    public void onOfferConfirmation(Vessel vessel, Boolean isConfirmed){
        vessel.offerConfirmation(isConfirmed);
        vesselRepo.saveAndFlush(vessel);
        if(!isConfirmed){
            sendUndoReservationToAllServices(vessel.getVesselId());
            vesselRepo.deleteById(vessel.getVesselId());
        }
    }

    private void sendUndoReservationToAllServices(String vesselId){
        UndoReservationCommand undoReservationCommand = new UndoReservationCommand(vesselId);
        commandDispatcher.sendUndoBerthReservationCommand(undoReservationCommand);
        commandDispatcher.sendUndoServiceReservationCommand(undoReservationCommand);
        commandDispatcher.sendUndoTowingPilotageReservationCommand(undoReservationCommand);
        DeleteOfferCommand deleteOfferCommand = new DeleteOfferCommand(vesselId);
        commandDispatcher.sendDeleteOfferCommand(deleteOfferCommand);
    }

}
