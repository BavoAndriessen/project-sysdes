package be.ugent.systemdesign.ligplaats.application;

import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthResponse;
import be.ugent.systemdesign.ligplaats.application.command.UnloadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.event.EventDispatcher;
import be.ugent.systemdesign.ligplaats.application.event.ShipReadyEvent;
import be.ugent.systemdesign.ligplaats.domain.Berth;
import be.ugent.systemdesign.ligplaats.domain.BerthRepository;
import be.ugent.systemdesign.ligplaats.domain.BerthState;
import be.ugent.systemdesign.ligplaats.domain.BerthWorkerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class BerthServiceImpl implements BerthService{

    @Autowired
    BerthRepository berthRepo;



    @Override
    public ReserveBerthResponse reserveBerth(Double size, String vesselId) {
        Berth b;
        try {
            Random rand = new Random();
            List<Berth> bs = berthRepo.findAllBySizeAndState(size, BerthState.AVAILABLE.name());
            b = bs.get(rand.nextInt(bs.size()));
            b.changeStateOfBerth(BerthState.RESERVED);
            b.setVesselId(vesselId);
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
            Berth b =  berthRepo.findByVesselId(vesselId);
            //System.out.println("found vessel with id : " + vesselId);
            b.setVesselId("");
            //System.out.println("vessel name removed from berth");
            b.changeStateOfBerth(BerthState.AVAILABLE);
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
            Berth b = berthRepo.findByVesselId(vesselId);
            b.changeStateOfBerth(BerthState.READY);
            berthRepo.save(b);
        }catch (Exception e){
            throw new Exception("problem at Berth, can't be ready now.");
        }
    }

    @Override
    public void setWorkerAtBerthAvailable(Integer berthId) throws Exception {
        try {
            Berth b = berthRepo.findById(berthId);
            b.getWorker().setState(BerthWorkerState.AVAILABLE);
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
        berthRepo.save(berth);
    }

    @Override
    public void handelUnloadContainersREST(UnloadContainersCommand command) throws Exception {
        Berth berth = berthRepo.findById(command.getBerthId());
        if (berth.getWorker().getState() == BerthWorkerState.BUSY){
            throw new Exception("worker is already busy");
        }
        berth.getWorker().setState(BerthWorkerState.BUSY);
        berthRepo.save(berth);
    }


    //@Override
    //public Response BerthNumberforReservation() {
    //    return null;
    //}

}
