package be.ugent.systemdesign.kapiteinsdienst.infrastructure;

import be.ugent.systemdesign.kapiteinsdienst.domain.Container;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    private String vesselId;
    private VesselStatus status;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;
    private Double vesselSize;
    private Double amountOfWaste;
    private Integer offerId;
    private Double price;
    private Boolean berthReserved;
    private Boolean towingPilotageReserved;
    private Boolean serviceReserved;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> additionalServices;
    @OneToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ContainerDataModel> containerList;

    public VesselDataModel(String vesselId,
                           VesselStatus status,
                           LocalDateTime arrivalDateTime,
                           Integer lengthOfStay,
                           Double vesselSize,
                           Double amountOfWaste,
                           Integer offerId,
                           Double price,
                           Boolean berthReserved,
                           Boolean towingPilotageReserved,
                           Boolean serviceReserved,
                           List<String> additionalServices,
                           List<Container> containerList) {
        this.vesselId = vesselId;
        this.status = status;
        this.arrivalDateTime = arrivalDateTime;
        this.lengthOfStay = lengthOfStay;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
        this.offerId = offerId;
        this.price = price;
        this.berthReserved = berthReserved;
        this.towingPilotageReserved = towingPilotageReserved;
        this.serviceReserved = serviceReserved;
        this.additionalServices = additionalServices;
        this.containerList = containerList.stream().map(e -> new ContainerDataModel(e.getContainerId(), e.getContents())).collect(Collectors.toList());
    }
}
