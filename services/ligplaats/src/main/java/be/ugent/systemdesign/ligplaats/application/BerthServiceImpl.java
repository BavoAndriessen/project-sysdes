package be.ugent.systemdesign.ligplaats.application;

import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthResponse;
import be.ugent.systemdesign.ligplaats.application.command.UnloadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.event.DockReadyEvent;
import be.ugent.systemdesign.ligplaats.application.event.EventDispatcher;
import be.ugent.systemdesign.ligplaats.application.event.EventHandler;
import be.ugent.systemdesign.ligplaats.application.event.ShipReadyEvent;
import be.ugent.systemdesign.ligplaats.domain.*;
import be.ugent.systemdesign.ligplaats.infrastructure.BerthDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class BerthServiceImpl implements BerthService{

    @Autowired
    BerthRepository berthRepo;

    @Autowired
    EventDispatcher eventDispatcher;

    @Override
    public ReserveBerthResponse reserveBerth(Double size, String vesselId) {
        Berth b;
        try {
            System.out.println("reserve berth opgeroepen");
            Random rand = new Random();
            List<Berth> bs = berthRepo.findAllBySizeAndState(size, BerthState.AVAILABLE.name());
            b = bs.get(rand.nextInt(bs.size()));
            b.changeStateOfBerth(BerthState.RESERVED);
            b.setVesselId(vesselId);
            System.out.println("berthId: " + b.getBerthId());
            berthRepo.save(b);
        } catch(NoBerthAvailableException e) {
            return new ReserveBerthResponse(ResponseStatus.FAIL,"there is no berth available");
        } catch(RuntimeException e) {
            return new ReserveBerthResponse(ResponseStatus.FAIL,"Berth could not be reserved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ReserveBerthResponse(ResponseStatus.SUCCESS,"berth reserved");
    }

    @Override
    public void undoReservation(String vesselId) throws Exception {
        try {
            System.out.println("undoReservation berth opgeroepen");
            Berth b =  berthRepo.findByVesselId(vesselId);
            //System.out.println("found vessel with id : " + vesselId);
            b.setVesselId("");
            //System.out.println("vessel name removed from berth");
            b.changeStateOfBerth(BerthState.AVAILABLE);
            b.getWorker().setState(BerthWorkerState.AVAILABLE);
            //System.out.println("status changed");
            berthRepo.save(b);
            //System.out.println("status is saved");
        }catch (Exception e){
            throw new RuntimeException("reservation could not be undone");
        }

    }

    @Override
    public void setBerthReady(String vesselId) throws Exception {
        try{
            System.out.println("setBerthReady berth opgeroepen");
            Berth b = berthRepo.findByVesselId(vesselId);
            b.changeStateOfBerth(BerthState.READY);
            berthRepo.save(b);
        }catch (Exception e){
            throw new Exception("problem at Berth, can't be ready now.");
        }
    }

    @Override
    public void setWorkerStatusAtDock(BerthWorkerState state,Integer berthId) throws Exception {
        try {
            Berth b = berthRepo.findById(berthId);
            b.getWorker().setState(state);
            berthRepo.save(b);
            TimeUnit.SECONDS.sleep(2);
            //het kan zijn dat de vesselId leeg is, dan zit het programma vast, dus gebruikt men een "if" voor de veiligheid
            //als de worker beschikbaar is, dan is hij/zij klaar met laden/ontladen van de containers


        }catch (Exception e){
            throw new Exception("BerthServiceImpl -> setWorkerAtBerthAvailable -> can't set worker available.");
        }
    }

    @Override
    public void handelLoadContainersREST(LoadContainersCommand command) throws Exception {
        Berth berth = berthRepo.findById(command.getBerthId());
        if (berth.getWorker().getState() == BerthWorkerState.BUSY){
            throw new Exception("worker is already busy");
        }
        berth.getWorker().setState(BerthWorkerState.BUSY);
        System.out.println(" loading containers");
        berthRepo.save(berth);
    }

    @Override
    public void handelUnloadContainersREST(UnloadContainersCommand command) throws Exception {
        Berth berth = berthRepo.findById(command.getBerthId());
        if (berth.getWorker().getState() == BerthWorkerState.BUSY){
            throw new Exception("worker is already busy");
        }
        berth.getWorker().setState(BerthWorkerState.BUSY);
        System.out.println(" unloading containers");
        berthRepo.save(berth);
    }

    @Override
    public void fillRepository() {

        berthRepo.flushRepo();
        Berth b;
        List<Berth> l = new ArrayList<Berth>();
        for(int i=0; i<10;i++){
            b = new Berth(
                    i,
                    (i + 1) *2.0,
                    BerthState.AVAILABLE,
                    i+1,
                    new BerthWorker(i+2,BerthWorkerState.AVAILABLE,i),
                    true,
                    "ship-"+i
            );
            l.add(b);
        }
        l.forEach(elt-> {
            try {
                berthRepo.save(elt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void sendshipReady(Integer berthId) throws Exception {
        Berth b = berthRepo.findById(berthId);
        String vesselId = b.getVesselId();
        int berthNumber = b.getBerthNumber();
        eventDispatcher.sendShipReadyEvent(new ShipReadyEvent(vesselId, berthNumber));
    }

    @Override
    public void sendDockReady(String vesselId) throws Exception {
        Berth b = berthRepo.findByVesselId(vesselId);
        int berthNumber = b.getBerthNumber();
        Integer berthId = b.getBerthId();
        eventDispatcher.sendDockReadyEvent(new DockReadyEvent(berthId, berthNumber));
    }

    //@Override
    //public Response BerthNumberforReservation() {
    //    return null;
    //}

}
