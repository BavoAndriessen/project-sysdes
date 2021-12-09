package be.ugent.systemdesign.kapiteinsdienst.application.command;

import be.ugent.systemdesign.kapiteinsdienst.domain.Container;
import be.ugent.systemdesign.kapiteinsdienst.domain.Crew;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class RequestOfferCommand {

    private String vesselId;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;
    private Double vesselSize;
    private Double amountOfWaste;
    private List<Container> containerList;
    private List<Crew> crewList;

    private String responseDestination;

    public RequestOfferCommand(String vesselId, LocalDateTime arrivalDateTime, Integer lengthOfStay, Double vesselSize, Double amountOfWaste, List<Container> containerList, List<Crew> crewList) {
        this.vesselId = vesselId;
        this.arrivalDateTime = arrivalDateTime;
        this.lengthOfStay = lengthOfStay;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
        this.containerList = containerList;
        this.crewList = crewList;
    }
}
