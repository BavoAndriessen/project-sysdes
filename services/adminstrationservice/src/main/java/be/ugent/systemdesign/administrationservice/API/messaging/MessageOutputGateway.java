package be.ugent.systemdesign.administrationservice.API.messaging;

import be.ugent.systemdesign.administrationservice.application.event.EventDispatcher;
import be.ugent.systemdesign.administrationservice.domain.NewContainerListEvent;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@Service
@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher {

    @Override
    @Gateway(requestChannel = Channels.NEW_CONTAINER_LIST)
    public void publishNewContainerListEvent(NewContainerListEvent event);
}
