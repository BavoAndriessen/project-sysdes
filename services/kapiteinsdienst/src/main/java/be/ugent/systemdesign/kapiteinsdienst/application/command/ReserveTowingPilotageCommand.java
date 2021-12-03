package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReserveTowingPilotageCommand {

    private String vesselId;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;

    private String responseDestination;

    public ReserveTowingPilotageCommand(String vesselId, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {
        this.vesselId = vesselId;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }
}
