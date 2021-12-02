package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.AcceptOfferCommand;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.CreateOfferCommand;
import be.ugent.systemdesign.kapiteinsdienst.application.command.client.RefuseOfferCommand;
import be.ugent.systemdesign.kapiteinsdienst.application.saga.VesselRegistrationSaga;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler {

    @Autowired
    VesselRepository vesselRepo;

    @Autowired
    VesselRegistrationSaga vesselRegistrationSaga;

    public void handleCreateOfferCommand(CreateOfferCommand command){
        try {
            Vessel vessel = new Vessel(
                    command.getVesselNumber(),
                    command.getArrivalDateTime(),
                    command.getDepartureDateTime(),
                    command.getVesselSize(),
                    command.getAmountOfWaste(),
                    command.getAdditionalServices(),
                    command.getContainerList(),
                    command.getCrewList());

            synchronized (vesselRegistrationSaga){
                vesselRegistrationSaga.startVesselRegistration(vessel);
            }


        } catch (RuntimeException e){

        }

    }
    //TODO aanvullen
    public void handleAcceptOfferCommand(AcceptOfferCommand command){

    }
    //TODO aanvullen
    public void handleRefuseOfferCommand(RefuseOfferCommand command){

    }

    public void handleReserveBerthResponse(ReserveBerthResponse response){
        try{
            Vessel vessel = vesselRepo.findById(response.getVesselNumber());
            if(response.getStatus() == ResponseStatus.FAIL){
                synchronized (vesselRegistrationSaga){
                    vesselRegistrationSaga.onReservationFail(vessel);
                }
            }
        } catch (RuntimeException e){

        }
    }

    public void handleReserveServiceResponse(ReserveServiceResponse response){
        try{
            Vessel vessel = vesselRepo.findById(response.getVesselNumber());
            if(response.getStatus() == ResponseStatus.FAIL){
                synchronized (vesselRegistrationSaga){
                    vesselRegistrationSaga.onReservationFail(vessel);
                }
            }
        } catch (RuntimeException e){

        }
    }

    public void handleReserveTowingPilotageResponse(ReserveTowingPilotageResponse response){
        try{
            Vessel vessel = vesselRepo.findById(response.getVesselNumber());
            if(response.getStatus() == ResponseStatus.FAIL){
                synchronized (vesselRegistrationSaga){
                    vesselRegistrationSaga.onReservationFail(vessel);
                }
            }
        } catch (RuntimeException e){

        }
    }

    public void handleOfferCreatedResponse(OfferCreatedResponse response) {
        try {
            Vessel vessel = vesselRepo.findById(response.getVesselNumber());
            if (response.getStatus() == ResponseStatus.SUCCESS){
                synchronized (vesselRegistrationSaga){
                    vesselRegistrationSaga.onOfferCreatedByAdministration(vessel, response.getOfferId(), response.getPrice());
                }
            }
            else {
                synchronized (vesselRegistrationSaga) {
                    vesselRegistrationSaga.onReservationFail(vessel);
                }
            }
        } catch (RuntimeException e) {

        }
    }
}
