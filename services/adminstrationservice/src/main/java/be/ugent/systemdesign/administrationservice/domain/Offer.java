package be.ugent.systemdesign.administrationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class Offer {
    private static int idCounter = 0;

    private Integer offerId;
    private Double price;
    private Integer lengthOfStay;
    private LocalDateTime arrivalTime;
    private Double vesselSize;
    private Double amountOfWaste;

    public static Integer getNewId(){
        return idCounter++;
    }
}
