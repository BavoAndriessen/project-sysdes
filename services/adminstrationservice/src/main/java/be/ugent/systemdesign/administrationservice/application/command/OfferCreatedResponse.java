package be.ugent.systemdesign.administrationservice.application.command;

import be.ugent.systemdesign.administrationservice.application.Response;
import be.ugent.systemdesign.administrationservice.application.ResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferCreatedResponse extends Response {
    private Integer offerId;
    private String vesselId;
    private Double price;

    public OfferCreatedResponse(ResponseStatus status, String message, Integer offerId, String vesselId, Double price) {
        super(status, message);
        this.offerId = offerId;
        this.vesselId = vesselId;
        this.price = price;
    }
}
