package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReserveTowingPilotageCommand {

    private String vesselId;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;

    private String responseDestination;

    public ReserveTowingPilotageCommand(String vesselId, LocalDateTime arrivalDateTime, Integer lengthOfStay) {
        this.vesselId = vesselId;
        this.arrivalDateTime = arrivalDateTime;
        this.lengthOfStay = lengthOfStay;
    }
}
