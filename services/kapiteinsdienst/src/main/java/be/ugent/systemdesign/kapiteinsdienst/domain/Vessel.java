package be.ugent.systemdesign.kapiteinsdienst.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Vessel {

    private Integer vesselNumber;
    private VesselStatus status;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    private Double vesselSize;
    private Double amountOfWaste;
    private Integer offerId;
    private Double price;
    private List<String> additionalServices;
    private List<Container> containerList;
    private List<Crew> crewList;

    public Vessel(Integer vesselNumber, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, Double vesselSize, Double amountOfWaste, List<String> additionalServices, List<Container> containerList, List<Crew> crewList) {
        this.vesselNumber = vesselNumber;
        this.status = null;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
        this.offerId = null;
        this.price = null;
        this.additionalServices = additionalServices;
        this.containerList = containerList;
        this.crewList = crewList;
    }
}
