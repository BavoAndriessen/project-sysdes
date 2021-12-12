package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReserveServiceCommand {

    private String vesselId;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;
    private List<String> additionalServices;

    private String responseDestination;

    public ReserveServiceCommand(String vesselId, LocalDateTime arrivalDateTime, Integer lengthOfStay, List<String> additionalServices) {
        this.vesselId = vesselId;
        this.arrivalDateTime = arrivalDateTime;
        this.lengthOfStay = lengthOfStay;
        this.additionalServices = additionalServices;
    }
}
