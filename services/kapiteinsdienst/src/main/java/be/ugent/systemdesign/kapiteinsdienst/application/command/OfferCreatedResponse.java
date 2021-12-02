package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferCreatedResponse extends Response {

    private Integer offerId;
    private Integer vesselNumber;
    private Double price;

    public OfferCreatedResponse(ResponseStatus status, String message, Integer offerId, Integer vesselNumber, Double price){
        super(status, message);
        this.offerId = offerId;
        this.vesselNumber = vesselNumber;
        this.price = price;
    }


}
