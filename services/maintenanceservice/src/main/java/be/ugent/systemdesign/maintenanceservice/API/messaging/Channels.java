package be.ugent.systemdesign.maintenanceservice.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    static final String RESERVE_SERVICE = "reserve_service";
    static final String SERVICE_RESERVED = "service_reserved";
    static final String UNDO_SERVICE_RESERVATION = "undo_service_reservation";

    @Output(SERVICE_RESERVED)
    MessageChannel serviceReserved();

    @Input(RESERVE_SERVICE)
    SubscribableChannel reserveService();

    @Input(UNDO_SERVICE_RESERVATION)
    SubscribableChannel undoServiceReservation();


}
