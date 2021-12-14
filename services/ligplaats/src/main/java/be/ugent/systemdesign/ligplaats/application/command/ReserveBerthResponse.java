package be.ugent.systemdesign.ligplaats.application.command;

import be.ugent.systemdesign.ligplaats.application.Response;
import be.ugent.systemdesign.ligplaats.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
