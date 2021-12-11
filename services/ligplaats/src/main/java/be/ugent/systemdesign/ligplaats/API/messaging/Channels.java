package be.ugent.systemdesign.ligplaats.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface Channels {

    static final String DOCK_READY_EVENT = "dock_ready";//outgoing
    @Output(DOCK_READY_EVENT)
    MessageChannel dockReadyEvent();

    static final String BERTH_RESERVED_REPLY = "berth_reserved";//outgoing
    @Output(BERTH_RESERVED_REPLY)
    MessageChannel berthReservedResponse();

    static final String SHIP_READY_EVENT = "ship_ready";//outgoing
    @Output(SHIP_READY_EVENT)
    MessageChannel shipReadyEvent();



    static final String SHIP_ARRIVING_EVENT = "ship_arriving"; //incoming
    @Input(SHIP_ARRIVING_EVENT)
    SubscribableChannel shipArrivingEvent();

    static final String CONTAINERS_READY_AT_DOCK_EVENT = "containers_ready_at_dock";//incoming
    @Input(CONTAINERS_READY_AT_DOCK_EVENT)
    SubscribableChannel containersReadyAtDockEventEvent();

    static final String RESERVE_BERTH_COMMAND = "reserve_berth";//incoming
    @Input(RESERVE_BERTH_COMMAND)
    SubscribableChannel reserveBerthCommand();


    static final String UNDO_BERTH_RESERVATION_EVENT = "undo_berth_reservation";//incoming
    @Input(UNDO_BERTH_RESERVATION_EVENT)
    SubscribableChannel undoBerthReservationCommand();

    //static final String BERTH_NUMBER_FOR_RESERVATION_COMMAND = "berth_number_for_reservation";//incoming
    //@Input(BERTH_NUMBER_FOR_RESERVATION_COMMAND)
    //SubscribableChannel berthNumberForReservationCommand();
}
