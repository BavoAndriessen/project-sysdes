package be.ugent.systemdesign.towingpilotageservice.application.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ReserveTowingPilotageResponse extends Response {

    private String vesselId;

    public ReserveTowingPilotageResponse(ResponseStatus status, String message, String vesselId) {
        super(status, message);
        this.vesselId = vesselId;
    }
}
