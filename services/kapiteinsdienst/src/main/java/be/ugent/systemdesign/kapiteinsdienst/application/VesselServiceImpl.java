package be.ugent.systemdesign.kapiteinsdienst.application;

import be.ugent.systemdesign.kapiteinsdienst.application.saga.VesselRegistrationSaga;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselStatus;
import be.ugent.systemdesign.kapiteinsdienst.infrastructure.VesselNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VesselServiceImpl implements VesselService {

    @Autowired
    VesselRepository vesselRepo;

    @Autowired
    VesselRegistrationSaga vesselRegistrationSaga;

    private int counter = 0;
    @Override
    public Response registerNewVessel(Vessel vessel) {

        try{
            vesselRepo.findById(vessel.getVesselId());
            return new Response(ResponseStatus.FAIL, "Vessel "+vessel.getVesselId()+" already registered");

        } catch (VesselNotFoundException e){
            try {
                synchronized (vesselRegistrationSaga){
                    vesselRegistrationSaga.startVesselRegistration(vessel);
                }
                return new Response(ResponseStatus.SUCCESS,"Vessel "+vessel.getVesselId()+" registered");
            } catch (RuntimeException ex){
                return new Response(ResponseStatus.FAIL,"Vessel "+vessel.getVesselId()+" not registered");
            }
        }


    }

    @Override
    public Response handleOfferConfirmation(String vesselId, Boolean isAccepted) {
        try {
            Vessel vessel = vesselRepo.findById(vesselId);
            synchronized (vesselRegistrationSaga){
                /*vessel.setServiceReserved(true);
                vessel.setBerthReserved(true);
                vessel.setTowingPilotageReserved(true);
                if(counter == 0){
                    vessel.setStatus(VesselStatus.OFFER_CREATED);
                    counter++;
                }*/
                if(vessel.checkOfferAvailability()){
                    vesselRegistrationSaga.onOfferConfirmation(vessel, isAccepted);
                    return new Response(ResponseStatus.SUCCESS, isAccepted?"Offer for vessel "+vesselId+" accepted by scheepsagent":"Offer for vessel "+vesselId+" not accepted by scheepsagent");
                } else {
                    return new Response(ResponseStatus.FAIL, (vessel.getStatus() == VesselStatus.ACCEPTED || vessel.getStatus() == VesselStatus.REFUSED)? "Offer for vessel "+vesselId+" was already "+vessel.getStatus().toString().toLowerCase():"Offer for vessel "+vesselId+" is not ready to be confirmed");
                }
            }

        } catch (RuntimeException e){
            return new Response(ResponseStatus.FAIL, "Offer for vessel "+vesselId+" could neither be accepted nor denied");
        }
    }
}
