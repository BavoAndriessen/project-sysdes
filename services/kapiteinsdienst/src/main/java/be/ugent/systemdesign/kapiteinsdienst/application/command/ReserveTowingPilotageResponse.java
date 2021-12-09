package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReserveTowingPilotageResponse extends Response {

    private String vesselId;

    public ReserveTowingPilotageResponse(ResponseStatus status, String message, String vesselId) {
        super(status, message);
        this.vesselId = vesselId;
    }
}
