package be.ugent.systemdesign.kapiteinsdienst.application;

import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;

public interface VesselService {

    Response registerNewVessel(Vessel vessel);
    Response handleOfferConfirmation(String vesselId, Boolean isAccepted);
}
