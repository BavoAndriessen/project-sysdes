package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReserveTowingPilotageCommand {

    private Integer vesselNumber;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;

    private String responseDestination;

    public ReserveTowingPilotageCommand(Integer vesselNumber, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {
        this.vesselNumber = vesselNumber;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }
}
