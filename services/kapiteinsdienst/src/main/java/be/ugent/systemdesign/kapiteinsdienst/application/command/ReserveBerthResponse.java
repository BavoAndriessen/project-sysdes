package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReserveBerthResponse extends Response {

    private Integer vesselNumber;

    public ReserveBerthResponse(ResponseStatus status, String message, Integer vesselNumber) {
        super(status, message);
        this.vesselNumber = vesselNumber;
    }
}
