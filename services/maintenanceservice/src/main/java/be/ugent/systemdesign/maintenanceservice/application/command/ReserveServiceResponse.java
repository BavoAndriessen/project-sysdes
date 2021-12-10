package be.ugent.systemdesign.maintenanceservice.application.command;

import be.ugent.systemdesign.maintenanceservice.application.Response;
import be.ugent.systemdesign.maintenanceservice.application.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ReserveServiceResponse extends Response {

    private String vesselId;

    public ReserveServiceResponse(ResponseStatus status, String message, String vesselId) {
        super(status, message);
        this.vesselId = vesselId;
    }
}
