package be.ugent.systemdesign.administrationservice.infrastructure.document;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@Getter
public class OfferDataModel {
//    @Id
    private Integer offerId;
    private Double price;
    private Integer lengthOfStay;
    private LocalDateTime arrivalTime;
    private Double vesselSize;
    private Double amountOfWaste;

    public OfferDataModel(Integer offerId, Double price, Integer lengthOfStay,
                          LocalDateTime arrivalTime, Double vesselSize, Double amountOfWaste){
        this.offerId = offerId;
        this.price = price;
        this.lengthOfStay = lengthOfStay;
        this.arrivalTime = arrivalTime;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
    }
}
