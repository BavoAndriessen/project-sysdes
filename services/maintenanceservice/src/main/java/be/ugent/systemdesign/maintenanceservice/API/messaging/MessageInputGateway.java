package be.ugent.systemdesign.maintenanceservice.API.messaging;

import be.ugent.systemdesign.maintenanceservice.application.ResponseStatus;
import be.ugent.systemdesign.maintenanceservice.application.command.ReserveServiceCommand;
import be.ugent.systemdesign.maintenanceservice.application.command.ReserveServiceResponse;
import be.ugent.systemdesign.maintenanceservice.application.command.UndoReservationCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    private static final Logger log = LoggerFactory.getLogger(MessageInputGateway.class);

    @Autowired
    Channels channels;

    @StreamListener(Channels.RESERVE_SERVICE)
    public void consumeServiceCommand(ReserveServiceCommand command) {
        ReserveServiceResponse response = new ReserveServiceResponse(ResponseStatus.SUCCESS, "successful service reservation", command.getVesselId());
        channels.serviceReserved().send(
                MessageBuilder
                        .withPayload(response)
                        .setHeader("spring.cloud.stream.sendto.destination", command.getResponseDestination())
                        .build()
        );
    }

    @StreamListener(Channels.UNDO_SERVICE_RESERVATION)
    public void consumeUndoReservation(UndoReservationCommand command){
        log.info("Received undoreservation for vessel {}", command.getVesselId());
    }
}
