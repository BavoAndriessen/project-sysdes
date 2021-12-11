package be.ugent.systemdesign.ligplaats.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BerthWorker {
    private final Integer id;
    private BerthWorkerState state;
    private int berthId;
    public BerthWorker(int id, BerthWorkerState newState, int berthId){
        this.id = id;
        this.state = newState;
        this.berthId = berthId;
    }

    public void changeState(BerthWorkerState newState){
        this.state = newState;
    }
    public boolean workerIsBusy(){
        return this.state == BerthWorkerState.BUSY || this.state == BerthWorkerState.AWAY;
    }
    public boolean workerIsREADY(){
        return this.state == BerthWorkerState.READY;
    }
    public boolean workerIsAvailable(){
        return this.state == BerthWorkerState.AVAILABLE;
    }
    @Override
    public String toString(){
        return "Worker{" +
                "Id=" + id +
                ", berthId = " + berthId + '\'' +
                ", state = " + state.name() +
                '}';
    }
}
