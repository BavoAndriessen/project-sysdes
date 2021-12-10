package be.ugent.systemdesign.kapiteinsdienst.application;

import be.ugent.systemdesign.kapiteinsdienst.application.saga.VesselRegistrationSaga;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselStatus;
import be.ugent.systemdesign.kapiteinsdienst.infrastructure.VesselNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VesselServiceImpl implements VesselService {

    @Autowired
    VesselRepository vesselRepo;

    @Autowired
    VesselRegistrationSaga vesselRegistrationSaga;

    @Override
    public Response registerNewVessel(Vessel vessel) {
        synchronized (vesselRegistrationSaga){
            try{
                vesselRepo.findById(vessel.getVesselId());
                return new Response(ResponseStatus.FAIL, "Vessel "+vessel.getVesselId()+" already registered");
            } catch (VesselNotFoundException e){
                try {
                    vesselRegistrationSaga.startVesselRegistration(vessel);
                    return new Response(ResponseStatus.SUCCESS,"Vessel "+vessel.getVesselId()+" registered");
                } catch (RuntimeException ex){
                    return new Response(ResponseStatus.FAIL,"Vessel "+vessel.getVesselId()+" not registered");
                }
            }
        }
    }

    @Override
    public Response handleOfferConfirmation(String vesselId, Boolean isAccepted) {
        synchronized (vesselRegistrationSaga){
            try {
                Vessel vessel = vesselRepo.findById(vesselId);
                    if(vessel.checkOfferAvailability()){
                        vesselRegistrationSaga.onOfferConfirmation(vessel, isAccepted);
                        return new Response(ResponseStatus.SUCCESS, isAccepted?"Offer for vessel "+vesselId+" accepted by scheepsagent":"Offer for vessel "+vesselId+" not accepted by scheepsagent");
                    } else {
                        return new Response(ResponseStatus.FAIL, (vessel.getStatus() == VesselStatus.ACCEPTED || vessel.getStatus() == VesselStatus.REFUSED)? "Offer for vessel "+vesselId+" was already "+vessel.getStatus().toString().toLowerCase():"Offer for vessel "+vesselId+" is not ready to be confirmed");
                    }
            } catch (VesselNotFoundException e){
                return new Response(ResponseStatus.FAIL, "Vessel "+vesselId+" is not registered");
            } catch (RuntimeException e){
                return new Response(ResponseStatus.FAIL, "Offer for vessel "+vesselId+" could neither be accepted nor denied");
            }
        }
    }
}
