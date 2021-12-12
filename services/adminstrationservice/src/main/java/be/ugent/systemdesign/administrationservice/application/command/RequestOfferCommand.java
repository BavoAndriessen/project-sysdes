package be.ugent.systemdesign.administrationservice.application.command;

import be.ugent.systemdesign.administrationservice.domain.Container;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestOfferCommand {
    private String vesselId;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;
    private Double vesselSize;
    private Double amountOfWaste;
    private List<Container> containerList;
}
