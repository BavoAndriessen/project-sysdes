package be.ugent.systemdesign.towingpilotageservice.API;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    static final String RESERVE_TOWING_PILOTAGE = "reserve_towing_pilotage";
    static final String TOWING_PILOTAGE_RESERVED = "towing_pilotage_reserved";
    static final String UNDO_TOWING_PILOTAGE_RESERVATION = "undo_towing_pilotage_reservation";
    static final String NAVIGATE_SHIP = "navigate_ship";
    static final String ARRIVED = "arrived";

    @Output(TOWING_PILOTAGE_RESERVED)
    MessageChannel towingPilotageReserved();

    @Input(RESERVE_TOWING_PILOTAGE)
    SubscribableChannel reserveTowingPilotage();

    @Input(UNDO_TOWING_PILOTAGE_RESERVATION)
    SubscribableChannel undoTowingPilotageReservation();

    @Input(NAVIGATE_SHIP)
    SubscribableChannel navigateShip();

    @Output(ARRIVED)
    MessageChannel arrived();


}
