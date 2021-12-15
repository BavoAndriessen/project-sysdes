package be.ugent.systemdesign.ligplaats.application.event;


import be.ugent.systemdesign.ligplaats.application.BerthService;
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
          bs.handelShipArriving(e);
      }catch (Exception ex){
          throw new Exception("berth can't be ready now.");
      }
    }

    @Async
    @TransactionalEventListener
    public void handelContainersReadyAtDock(ContainersReadyAtDockEvent e) throws Exception {
        //bs.setWorkerAtBerthAvailable(e.getBerthId());

        try {
            bs.handelContainersReadyAtDock(e);

        }catch (Exception ex){
            throw new Exception("error in handelContainerReadyAtDock.");
        }

    }

}

