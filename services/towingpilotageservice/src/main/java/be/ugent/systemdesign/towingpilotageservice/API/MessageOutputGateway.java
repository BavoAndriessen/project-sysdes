package be.ugent.systemdesign.towingpilotageservice.API;

import be.ugent.systemdesign.towingpilotageservice.application.event.ArrivedEvent;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway
public interface MessageOutputGateway {

    @Gateway(requestChannel = Channels.ARRIVED)
    void publishArrivedEvent(ArrivedEvent event);
}
