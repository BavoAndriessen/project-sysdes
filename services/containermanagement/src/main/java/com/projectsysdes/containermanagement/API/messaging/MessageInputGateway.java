package com.projectsysdes.containermanagement.API.messaging;

import com.projectsysdes.containermanagement.application.event.EventListener;
import com.projectsysdes.containermanagement.domain.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MessageInputGateway {

    @Autowired
    EventListener eventListener;


    @StreamListener(Channels.NEW_CONTAINER_LIST)
    void consumeNewContainerListEvent(NewContainerListEvent event) {
        eventListener.consumeNewContainerListEvent(event);
    }

}
