package be.ugent.systemdesign.ligplaats.API.messaging;

import be.ugent.systemdesign.ligplaats.application.command.CommandHandler;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthCommand;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthResponse;
import be.ugent.systemdesign.ligplaats.application.command.UndoReservationCommand;
import be.ugent.systemdesign.ligplaats.application.event.ContainersReadyAtDockEvent;
import be.ugent.systemdesign.ligplaats.application.event.EventHandler;
import be.ugent.systemdesign.ligplaats.application.event.ShipArrivingEvent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageInputGateway {

    @Autowired
    EventHandler eventHandler;
    @Autowired
    CommandHandler commandHandler;

    @StreamListener(Channels.SHIP_ARRIVING_EVENT)
    public void receiveShipArrivingEvent(ShipArrivingEvent event) throws Exception {
        eventHandler.handelShipArriving(event);
    }
    @StreamListener(Channels.CONTAINERS_READY_AT_DOCK_EVENT)
    public void receiveContainerReadyAtDockEvent(ContainersReadyAtDockEvent e) throws Exception {
        eventHandler.handelContainersReadyAtDock(e);
    }
    @StreamListener(Channels.RESERVE_BERTH_COMMAND)
    @SendTo(Channels.BERTH_RESERVED_REPLY)
    public ReserveBerthResponse receiveReserveBerthCommand(ReserveBerthCommand command) throws Exception {
        String vesselId = command.getVesselId();
        Double size = command.getVesselSize();
        ReserveBerthResponse response = commandHandler.handelReserveBerth(size, vesselId);
        return response;
    }
    @StreamListener(Channels.UNDO_BERTH_RESERVATION_EVENT)
    public void handelDockReadyEvent(UndoReservationCommand command) throws Exception {
        commandHandler.handelUndoReservation(command.getVesselId());
    }
}
