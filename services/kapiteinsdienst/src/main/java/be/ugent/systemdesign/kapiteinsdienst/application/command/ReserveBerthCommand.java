package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReserveBerthCommand {

    private Integer vesselNumber;
    private Double vesselSize;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;

    private String responseDestination;

    public ReserveBerthCommand(Integer vesselNumber, Double vesselSize, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {
        this.vesselNumber = vesselNumber;
        this.vesselSize = vesselSize;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
    }
}
