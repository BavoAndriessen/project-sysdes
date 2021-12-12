package be.ugent.systemdesign.administrationservice.infrastructure.document;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.time.LocalDateTime;

//WORDT NIET MEER GEBRUIKT => DOOR FINDBYVESSELID IS NU GEWOON IN DOCUMENTDATAMODEL
@Embeddable
@NoArgsConstructor
@Getter
public class VesselDataModel {
//    @Id
    private String vesselId;
    private LocalDateTime arrivalDate;

    public VesselDataModel(String vesselId, LocalDateTime arrivalDate){
        this.vesselId = vesselId;
        this.arrivalDate = arrivalDate;
    }
}
