package be.ugent.systemdesign.kapiteinsdienst.application.query;

public interface OfferQuery {

    public OfferReadModel getOfferByVesselId(String vesselId);
}
