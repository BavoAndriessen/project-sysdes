package be.ugent.systemdesign.kapiteinsdienst.application.command.client;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import lombok.Getter;

@Getter
public class OfferProposalResponse extends Response {

    private Integer vesselNumber;
    private Double price;
    private Integer offerId;

    public OfferProposalResponse(ResponseStatus status, String message, Integer vesselNumber, Double price, Integer offerId) {
        super(status, message);
        this.vesselNumber = vesselNumber;
        this.price = price;
        this.offerId = offerId;
    }
}
