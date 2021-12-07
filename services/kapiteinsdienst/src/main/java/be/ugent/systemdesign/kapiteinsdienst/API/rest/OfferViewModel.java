package be.ugent.systemdesign.kapiteinsdienst.API.rest;

import be.ugent.systemdesign.kapiteinsdienst.application.query.OfferReadModel;
import lombok.Getter;

@Getter
public class OfferViewModel {

    private String vesselId;
    private String offerId;
    private String price;

    public OfferViewModel(OfferReadModel offer) {
        vesselId = offer.getVesselId();
        offerId = offer.getOfferId().toString();
        price = offer.getPrice().toString();
    }


}