package be.ugent.systemdesign.ligplaats.application.command;

import be.ugent.systemdesign.ligplaats.application.Response;
import be.ugent.systemdesign.ligplaats.application.ResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReserveBerthResponse extends Response {

    String vesselId;
    public ReserveBerthResponse(ResponseStatus status, String message) {
        super(status, message);
    }

    public ReserveBerthResponse(ResponseStatus status, String message, String vesselId) {
        super(status, message);
        this.vesselId = vesselId;
    }
}
