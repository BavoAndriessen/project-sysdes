package be.ugent.systemdesign.ligplaats.infrastructure;


import be.ugent.systemdesign.ligplaats.domain.BerthState;
import be.ugent.systemdesign.ligplaats.domain.BerthWorkerState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BerthDataModel {
    @Id
    private Integer berthId;

    private Double size;
    private String state;
    private int berthNumber;
    private boolean dockReady;
    private String vesselId;
    private int workerId;
    private String workerState;
    public BerthDataModel(Integer berthId, Double size, String state, int berthNumber, boolean dockReady, String vesselId
            , int workerId, String workerState){
        this.berthId = berthId;
        this.berthNumber = berthNumber;
        this.size = size;
        this.dockReady = dockReady;
        this.state = state;
        this.workerId = workerId;
        this.workerState = workerState;
        this.vesselId = vesselId;
    }

    public boolean getDockReady() {
        return this.dockReady;
    }
    @Override
    public String toString() {
        return "Berth{" +
                "berthId=" + berthId +
                ", size=" + size +
                ", state=" + state +
                ", berth_number=" + berthNumber +
                ",vesselId = " + vesselId  +'}' +"\n"+
                "worker: {id="+workerId+" , state="+workerState+"}\n";
    }
}
