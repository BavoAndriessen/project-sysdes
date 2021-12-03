package be.ugent.systemdesign.kapiteinsdienst.application.command.client;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import lombok.Getter;

@Getter
public class OfferProposalResponse extends Response {

    private String vesselId;
    private Double price;
    private Integer offerId;

    public OfferProposalResponse(ResponseStatus status, String message, String vesselId, Double price, Integer offerId) {
        super(status, message);
        this.vesselId = vesselId;
        this.price = price;
        this.offerId = offerId;
    }
}
