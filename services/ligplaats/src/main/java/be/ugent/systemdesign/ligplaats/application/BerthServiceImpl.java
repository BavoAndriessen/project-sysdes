package be.ugent.systemdesign.ligplaats.application;

import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthResponse;
import be.ugent.systemdesign.ligplaats.application.command.UnloadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.event.*;
import be.ugent.systemdesign.ligplaats.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ReserveBerthResponse reserveBerth(Double size, String vesselId) throws Exception {
        Berth b;
        System.out.println("reserve berth opgeroepen");
        List<Berth> bs = berthRepo.findAllBySizeAndState(size, BerthState.AVAILABLE.name());
        if (bs.isEmpty()){
            System.out.println("failed");
            return new ReserveBerthResponse(ResponseStatus.FAIL,"there is no berth available with size:" +
                    " " + size + " and status:AVAILABLE");
        }
            //pak de eerste ligplaats van de lijst.
        b = bs.get(0);
        b.setState(BerthState.RESERVED);
        b.setVesselId(vesselId);
        System.out.println("berthId: " + b.getBerthId());
        berthRepo.save(b);
        return new ReserveBerthResponse(ResponseStatus.SUCCESS,"berth reserved", vesselId);

    }

    @Override
    public void undoReservation(String vesselId) throws Exception {
        try {
            System.out.println("undoReservation berth opgeroepen");
            Berth b =  berthRepo.findByVesselId(vesselId);

            b.setVesselId("");

            b.setState(BerthState.AVAILABLE);
            b.getWorker().setState(BerthWorkerState.AVAILABLE);

            berthRepo.save(b);

        }catch (Exception e){
            throw new RuntimeException("reservation could not be undone");
        }

    }

    @Override
    public void setBerthReady(String vesselId) throws Exception {
        try{
            System.out.println("setBerthReady berth opgeroepen");
            Berth b = berthRepo.findByVesselId(vesselId);
            System.out.println("found ligplaats with vesselId" +  b.getVesselId());
            b.setState(BerthState.READY);
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
            //TimeUnit.SECONDS.sleep(2);
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
        sendShipReady(command.getBerthId());
    }

    @Override
    public void handelUnloadContainersREST(UnloadContainersCommand command) throws Exception {
        Berth berth = berthRepo.findById(command.getBerthId());
        if (berth.getWorker().getState() == BerthWorkerState.BUSY){
            throw new Exception("worker is already busy");
        }
        berth.getWorker().setState(BerthWorkerState.BUSY);
        berthRepo.save(berth);
        //TimeUnit.SECONDS.sleep(1);
        System.out.println(" unloading containers");

        sendShipReady(command.getBerthId());
    }

    @Override
    public void fillRepository() {
        berthRepo.fillRepository();
    }


    @Override
    public void sendShipReady(Integer berthId) throws Exception {
        Berth b = berthRepo.findById(berthId);
        String vesselId = b.getVesselId();
        int berthNumber = b.getBerthNumber();

        eventDispatcher.sendShipReadyEvent(new ShipReadyEvent(vesselId, berthNumber));
        //waanneer de schip klaar staat, gaat die dan vertrekken en wordt de ligplaats en de dock werker
        //op status AVAILABLE gezet, dit is analoog aan undo reservatio, maar men heeft undoReservation
        //niet getriggerd want anders wordt een event naar kapiteins dienst gestuurd dat de reservatie ongedaan is
        //terwijn de reservatie met sucess afgelopen is.
        b.getWorker().setState(BerthWorkerState.AVAILABLE);
        b.setState(BerthState.AVAILABLE);
        b.setVesselId("");
        berthRepo.save(b);
    }

    @Override
    public void sendDockReady(String vesselId) throws Exception {
        Berth b = berthRepo.findByVesselId(vesselId);
        int berthNumber = b.getBerthNumber();
        Integer berthId = b.getBerthId();
        eventDispatcher.sendDockReadyEvent(new DockReadyEvent(berthId, berthNumber));
    }

    @Override
    public List<Berth> findAll() {
       return berthRepo.findAll();
    }

    @Override
    public void handelShipArriving(ShipArrivingEvent e) throws Exception {
        try {
            logger.info("handling ship arriving event ");
            System.out.println(e.getVesselId());
            setBerthReady(e.getVesselId());
            logger.info("berth ready for ship with id{ " + e.getVesselId() + "}.");
            logger.info("sending ship ready event");
            //TimeUnit.SECONDS.sleep(3);
            sendDockReady(e.getVesselId());
        }catch (Exception ex){
            throw new Exception("berth can't be ready now.");
        }
    }

    @Override
    public void handelContainersReadyAtDock(ContainersReadyAtDockEvent e) throws Exception {
        try {
            //bs.handelLoadContainersREST(new LoadContainersCommand(e.getBerthId()));
            logger.info("handling containers ready ay dock event ");
            setWorkerStatusAtDock(BerthWorkerState.BUSY, e.getBerthId());
            //TimeUnit.SECONDS.sleep(3);
            System.out.println("finished loading the containers, sending ship ship ready message to VTC");
            //sendShipReady(e.getBerthId());

        }catch (Exception ex){
            throw new Exception("error in handelContainerReadyAtDock.");
        }
    }

}
