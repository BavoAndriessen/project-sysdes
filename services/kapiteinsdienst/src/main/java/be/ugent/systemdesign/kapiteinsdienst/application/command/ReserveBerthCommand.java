package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReserveBerthCommand {

    private String vesselId;
    private Double vesselSize;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;

    private String responseDestination;

    public ReserveBerthCommand(String vesselId, Double vesselSize, LocalDateTime arrivalDateTime, Integer lengthOfStay) {
        this.vesselId = vesselId;
        this.vesselSize = vesselSize;
        this.arrivalDateTime = arrivalDateTime;
        this.lengthOfStay = lengthOfStay;
    }
}
