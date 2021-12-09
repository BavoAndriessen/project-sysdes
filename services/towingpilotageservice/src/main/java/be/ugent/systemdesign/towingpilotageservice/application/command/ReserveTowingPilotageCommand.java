package be.ugent.systemdesign.towingpilotageservice.application.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
