package be.ugent.systemdesign.ligplaats.application.event;


import be.ugent.systemdesign.ligplaats.application.command.UndoReservationCommand;
import org.springframework.stereotype.Service;

@Service
public interface EventDispatcher {

    void sendDockReadyEvent(DockReadyEvent e);
    void sendShipReadyEvent(ShipReadyEvent e);
}
