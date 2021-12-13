package be.ugent.systemdesign.administrationservice.infrastructure.document;

import be.ugent.systemdesign.administrationservice.domain.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class OfferDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer offerId;
    private Double price;
    private Integer lengthOfStay;
    private LocalDateTime arrivalTime;
    private Double vesselSize;
    private Double amountOfWaste;

    public OfferDataModel( Double price, Integer lengthOfStay,
                          LocalDateTime arrivalTime, Double vesselSize, Double amountOfWaste){
        //this.offerId = offerId;
        this.price = price;
        this.lengthOfStay = lengthOfStay;
        this.arrivalTime = arrivalTime;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
    }
}
