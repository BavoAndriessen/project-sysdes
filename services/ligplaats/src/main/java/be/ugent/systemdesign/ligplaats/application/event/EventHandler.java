package be.ugent.systemdesign.ligplaats.application.event;


import be.ugent.systemdesign.ligplaats.application.BerthService;
import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.domain.BerthWorkerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.TimeUnit;

@Service
public class EventHandler {
    @Autowired
    BerthService bs;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Async
    @TransactionalEventListener
    public void handelShipArriving(ShipArrivingEvent e) throws Exception {
      try {
          logger.info("handling ship arriving event ");
          bs.setBerthReady(e.getVesselId());
          logger.info("berth ready for ship with id{ " + e.getVesselId() + "}.");
          logger.info("sending ship ready event");
          TimeUnit.SECONDS.sleep(3);
          bs.sendDockReady(e.getVesselId());
      }catch (Exception ex){
          throw new Exception("berth can't be ready now.");
      }
    }

    @Async
    @TransactionalEventListener
    public void handelContainerReadyAtDock(ContainersReadyAtDockEvent e) throws Exception {
        //bs.setWorkerAtBerthAvailable(e.getBerthId());

        try {
            //bs.handelLoadContainersREST(new LoadContainersCommand(e.getBerthId()));
            logger.info("handling containers ready ay dock event ");
            bs.setWorkerStatusAtDock(BerthWorkerState.BUSY, e.getBerthId());
            TimeUnit.SECONDS.sleep(3);
            System.out.println("finished loading the containers, sending ship ship ready message to VTC");
            bs.sendshipReady(e.getBerthId());

        }catch (Exception ex){
            throw new Exception("error in handelContainerReadyAtDock.");
        }

    }

}

