package be.ugent.systemdesign.administrationservice.infrastructure.document;

import be.ugent.systemdesign.administrationservice.domain.Container;
import be.ugent.systemdesign.administrationservice.domain.Offer;
import be.ugent.systemdesign.administrationservice.domain.Vessel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DocumentDataModel {
    @Id
    private Integer documentId;
//    @Embedded
//    private VesselDataModel vessel;
    private String vesselId;
    private LocalDateTime arrivalDate;
    @Embedded
    private OfferDataModel offer;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ContainerDataModel> containerList;

    public DocumentDataModel(Integer documentId, Vessel vessel, Offer offer, List<Container> containerList){
        this.documentId = documentId;
//        this.vessel = new VesselDataModel(vessel.getVesselId(), vessel.getArrivalDate());
        this.vesselId = vessel.getVesselId();
        this.arrivalDate = vessel.getArrivalDate();
        this.offer = new OfferDataModel(
                offer.getOfferId(),
                offer.getPrice(),
                offer.getLengthOfStay(),
                offer.getArrivalTime(),
                offer.getVesselSize(),
                offer.getAmountOfWaste()
        );
        if(containerList != null) {
            this.containerList = containerList.stream()
                    .map(c -> new ContainerDataModel(
                            c.getContainerId(),
                            c.getContents()
                    )).collect(Collectors.toList());
        }
        else{
            this.containerList = null;
        }
    }
}
