package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReserveBerthResponse extends Response {

    private String vesselId;

    public ReserveBerthResponse(ResponseStatus status, String message, String vesselId) {
        super(status, message);
        this.vesselId = vesselId;
    }
}
