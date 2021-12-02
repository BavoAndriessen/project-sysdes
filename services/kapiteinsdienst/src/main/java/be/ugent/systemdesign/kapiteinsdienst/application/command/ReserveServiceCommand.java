package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReserveServiceCommand {

    private Integer vesselNumber;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    private List<String> additionalServices;

    private String responseDestination;

    public ReserveServiceCommand(Integer vesselNumber, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, List<String> additionalServices) {
        this.vesselNumber = vesselNumber;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.additionalServices = additionalServices;
    }
}
