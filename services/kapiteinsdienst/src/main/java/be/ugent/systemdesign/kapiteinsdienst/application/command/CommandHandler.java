package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import be.ugent.systemdesign.kapiteinsdienst.application.saga.VesselRegistrationSaga;
import be.ugent.systemdesign.kapiteinsdienst.domain.ReservationServices;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler {

    @Autowired
    VesselRepository vesselRepo;

    @Autowired
    VesselRegistrationSaga vesselRegistrationSaga;

    public void handleReserveBerthResponse(ReserveBerthResponse response){
        synchronized (vesselRegistrationSaga){
            try{
                if(response.getStatus() == ResponseStatus.SUCCESS){
                    vesselRegistrationSaga.onReservationSuccess(response.getVesselId(), ReservationServices.BERTH);
                } else {
                    vesselRegistrationSaga.onReservationFail(response.getVesselId());
                }
            } catch (RuntimeException e){
            }
        }
    }

    public void handleReserveServiceResponse(ReserveServiceResponse response){
        synchronized (vesselRegistrationSaga){
            try{
                if(response.getStatus() == ResponseStatus.SUCCESS){
                    vesselRegistrationSaga.onReservationSuccess(response.getVesselId(), ReservationServices.ADDITIONALSERVICES);
                } else {
                    vesselRegistrationSaga.onReservationFail(response.getVesselId());
                }
            } catch (RuntimeException e){
            }
        }
    }

    public void handleReserveTowingPilotageResponse(ReserveTowingPilotageResponse response){
        synchronized (vesselRegistrationSaga) {
            try {
                if (response.getStatus() == ResponseStatus.SUCCESS) {
                    vesselRegistrationSaga.onReservationSuccess(response.getVesselId(), ReservationServices.TOWINGPILOTAGE);
                } else {
                    vesselRegistrationSaga.onReservationFail(response.getVesselId());
                }
            } catch (RuntimeException e) {
            }
        }
    }

    public void handleOfferCreatedResponse(OfferCreatedResponse response) {
        synchronized (vesselRegistrationSaga){
            try {
                if (response.getStatus() == ResponseStatus.SUCCESS){
                    vesselRegistrationSaga.onOfferCreatedByAdministration(response.getVesselId(), response.getOfferId(), response.getPrice());
                }
                else {
                    vesselRegistrationSaga.onReservationFail(response.getVesselId());
                }
            } catch (RuntimeException e) {
            }
        }
    }

}
