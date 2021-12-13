package com.projectsysdes.containermanagement.API.messaging;

import com.projectsysdes.containermanagement.application.event.EventHandler;
import com.projectsysdes.containermanagement.domain.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    @Autowired
    EventHandler eventHandler;


    @StreamListener(Channels.NEW_CONTAINER_LIST)
    void consumeNewContainerListEvent(NewContainerListEvent event) {
        eventHandler.consumeNewContainerListEvent(event);
    }

}
