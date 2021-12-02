package be.ugent.systemdesign.kapiteinsdienst.infrastructure;

import be.ugent.systemdesign.kapiteinsdienst.domain.Container;
import be.ugent.systemdesign.kapiteinsdienst.domain.Crew;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VesselDataModel {

    @Id
    private Integer vesselNumber;
    private VesselStatus status;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    private Double vesselSize;
    private Double amountOfWaste;
    private Integer offerId;
    private Double price;
    private Boolean berthReserved;
    private Boolean towingPilotageReserved;
    private Boolean serviceReserved;
    @ElementCollection
    private List<String> additionalServices;
    @ManyToMany
    private List<ContainerDataModel> containerList;
    @ManyToMany
    private List<CrewDataModel> crewList;
    /*
    public VesselDataModel(Integer vesselNumber, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, Double vesselSize, Double amountOfWaste, List<String> additionalServices, List<Container> containerList, List<Crew> crewList) {
        this.vesselNumber = vesselNumber;
        this.status = null;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
        this.offerId = null;
        this.additionalServices = additionalServices;
        this.containerList = containerList.stream().map(e -> new ContainerDataModel(e.getContainerId(), e.getContents())).collect(Collectors.toList());
        this.crewList = crewList.stream().map(e -> new CrewDataModel(e.getCrewId(),e.getFirstName(),e.getLastName(),e.getDateOfBirth(),e.getType())).collect(Collectors.toList());
    }
    */
    public VesselDataModel(Integer vesselNumber,
                           VesselStatus status,
                           LocalDateTime arrivalDateTime,
                           LocalDateTime departureDateTime,
                           Double vesselSize,
                           Double amountOfWaste,
                           Integer offerId,
                           Double price,
                           Boolean berthReserved,
                           Boolean towingPilotageReserved,
                           Boolean serviceReserved,
                           List<String> additionalServices,
                           List<Container> containerList,
                           List<Crew> crewList) {
        this.vesselNumber = vesselNumber;
        this.status = status;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
        this.offerId = offerId;
        this.price = price;
        this.berthReserved = berthReserved;
        this.towingPilotageReserved = towingPilotageReserved;
        this.serviceReserved = serviceReserved;
        this.additionalServices = additionalServices;
        this.containerList = containerList.stream().map(e -> new ContainerDataModel(e.getContainerId(), e.getContents())).collect(Collectors.toList());
        this.crewList = crewList.stream().map(e -> new CrewDataModel(e.getCrewId(),e.getFirstName(),e.getLastName(),e.getDateOfBirth(),e.getType())).collect(Collectors.toList());
    }
}
