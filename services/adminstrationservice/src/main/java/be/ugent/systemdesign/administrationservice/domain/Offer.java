package be.ugent.systemdesign.administrationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Offer {
//    private static int idCounter = 0;

    private Integer offerId;
    private Double price;
    private Integer lengthOfStay;
    private LocalDateTime arrivalTime;
    private Double vesselSize;
    private Double amountOfWaste;

    public Offer(Double price, Integer lengthOfStay, LocalDateTime arrivalTime, Double vesselSize, Double amountOfWaste){
        this.price = price;
        this.lengthOfStay = lengthOfStay;
        this.arrivalTime = arrivalTime;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
    }

//    public static Integer getNewId(){
//        return idCounter++;
//    }
}
