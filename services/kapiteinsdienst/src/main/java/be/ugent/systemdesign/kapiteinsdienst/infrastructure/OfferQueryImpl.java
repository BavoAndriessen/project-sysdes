package be.ugent.systemdesign.kapiteinsdienst.infrastructure;

import be.ugent.systemdesign.kapiteinsdienst.application.query.OfferQuery;
import be.ugent.systemdesign.kapiteinsdienst.application.query.OfferReadModel;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferQueryImpl implements OfferQuery {

    @Autowired
    VesselRepository vesselRepo;

    @Override
    public OfferReadModel getOfferByVesselId(String vesselId) {
        try {
            Vessel vessel = vesselRepo.findById(vesselId);
            return mapToOfferReadModel(vessel);
        } catch (VesselNotFoundException | OfferNotFoundException e){
            throw e;
        }
    }

    private OfferReadModel mapToOfferReadModel(Vessel vessel){
        if(vessel.getOfferId() == null || vessel.getPrice() == null){
            throw new OfferNotFoundException();
        }
        return new OfferReadModel(vessel.getVesselId(),vessel.getOfferId(),vessel.getPrice());
        //TODO enkel voor test
        //return new OfferReadModel(vessel.getVesselId(),10,20.5);
    }
}
