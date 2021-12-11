package be.ugent.systemdesign.ligplaats.domain;

import lombok.*;

import java.util.Random;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Berth {
    private final Integer berthId;
    private final Double size;
    private BerthState state;
    private final int berthNumber;
    private BerthWorker worker;
    private boolean dockReady;
    private String vesselId;
    public Berth(int BerthId, Double size, BerthState state, int berthNumber, BerthWorker worker, boolean dockReady,String vesselId){
        this.berthId = BerthId;
        this.size = size;
        this.state = state;
        this.berthNumber = berthNumber;
        this.dockReady = dockReady;
        this.vesselId = vesselId;
        if(worker == null){
            this.addWorker();
        }
        else {
            this.worker = worker;
        }
    }
    public void changeStateOfBerth(BerthState newState){
        this.state = newState;
    }
    public boolean berthIsReady(){
        return this.state == BerthState.READY;
    }
    public boolean berthIsAvailable(){
        return this.state == BerthState.AVAILABLE;
    }

    public void reserveBerth(){
        this.state = BerthState.RESERVED;
    }
    public void undoReservation(){
        this.state = BerthState.AVAILABLE;
    }
    private void addWorker(){
        Random rand = new Random();
        worker = new BerthWorker(rand.nextInt(99999), BerthWorkerState.AVAILABLE, this.berthId);
    }

    public void changeStateOFDock(boolean state){
        this.dockReady = state;
    }
    @Override
    public String toString() {
        return "Berth{" +
                "BerthId=" + berthId +
                ", size='" + size + '\'' +
                ", state=" + state.name() +
                ", berth_number=" + berthNumber +
                ",vesselId = " + vesselId  +'}' + "\n"+
                worker.toString();
    }
}
