package be.ugent.systemdesign.vesseltrafficcontrol.application.event;

import be.ugent.systemdesign.vesseltrafficcontrol.application.Response;
import be.ugent.systemdesign.vesseltrafficcontrol.application.ResponseStatus;
import be.ugent.systemdesign.vesseltrafficcontrol.application.VTCService;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.ArrivedEvent;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.DockReadyEvent;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.ShipReadyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EventHandler {

    @Autowired
    VTCService vtcService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    @TransactionalEventListener
    public Response consumeArrivedEvent(ArrivedEvent event) {
        try {
            Response resp = vtcService.vesselArrived(event.getVesslId(), event.getDestination());
            logger.info(resp.status.name() + ": " + resp.message);
            return resp;
        } catch (Exception uniqueConstrainViolatedException) {
            return new Response( ResponseStatus.SUCCESS, "command already existed");
        }
    }

    @Async
    @TransactionalEventListener
    public Response consumeDockReadyEvent(DockReadyEvent event) {
        Response resp = vtcService.freeDock(event.getDockNumber());
        logger.info(resp.status.name() + ": " + resp.message);
        return resp;
    }

    @Async
    @TransactionalEventListener
    public Response consumeShipReadyEvent(ShipReadyEvent event) {
        // destination zero is the ocean
        Response resp = vtcService.findRoute(event.getVesselId(), 0);
        logger.info(resp.status.name() + ": " + resp.message);
        return resp;
    }
}
