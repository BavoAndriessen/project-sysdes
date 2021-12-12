package be.ugent.systemdesign.ligplaats.API.REST;


import be.ugent.systemdesign.ligplaats.application.query.BerthRealModel;
import lombok.Getter;

@Getter
public class BerthViewModel {
    private Integer berthId;
    private int berthNumber;
    private String vesselId;
    public BerthViewModel(BerthRealModel b){
        berthId = b.getBerthId();
        berthNumber = b.getBerthNumber();
        vesselId = b.getVesselId();
    }
}
