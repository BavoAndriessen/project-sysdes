package be.ugent.systemdesign.administrationservice.domain;

import be.ugent.systemdesign.administrationservice.domain.seedwork.AggregateRoot;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC) //TERUG NAAR PRIVATE ZETTEN
public class Document extends AggregateRoot {
    private static final Double PRICE_WASTE = 11.5;
    private static final Double PRICE_PER_SIZE_UNIT = 150.0;
    private static final Double PRICE_PER_HOUR = 1700.0;

//    private static int idCounter = 0;

    private Integer documentId;
    private Vessel vessel;
    private Offer offer;
    private List<Container> containerList;

    public Document(Vessel vessel, Offer offer, List<Container> containerList){
//        this.documentId = documentId;
        this.vessel = vessel;
        this.offer = offer;
        this.containerList = containerList;
//        if(containerList != null && containerList.size() != 0){
//            addDomainEvent(new NewContainerListEvent(containerList));
//        }
    }


    public static Double calculatePrice(Integer lengthOfStay, Double vesselSize, Double amountOfWaste){
        return lengthOfStay * PRICE_PER_HOUR + vesselSize * PRICE_PER_SIZE_UNIT + amountOfWaste * PRICE_WASTE;
    }

//    public static Integer getNewId(){
//        return idCounter++;
//    }
}
