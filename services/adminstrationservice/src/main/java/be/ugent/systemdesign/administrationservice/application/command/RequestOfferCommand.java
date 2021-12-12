package be.ugent.systemdesign.administrationservice.application.command;

import be.ugent.systemdesign.administrationservice.domain.Container;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RequestOfferCommand {
    private String vesselId;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;
    private Double vesselSize;
    private Double amountOfWaste;
    private List<Container> containerList;
}
