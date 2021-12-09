package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferCreatedResponse extends Response {

    private Integer offerId;
    private String vesselId;
    private Double price;

    public OfferCreatedResponse(ResponseStatus status, String message, Integer offerId, String vesselId, Double price){
        super(status, message);
        this.offerId = offerId;
        this.vesselId = vesselId;
        this.price = price;
    }


}
