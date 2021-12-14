package be.ugent.systemdesign.vesseltrafficcontrol.api.messaging;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.beans.EventHandler;

@Component
public class MessageInputGateway {

    EventHandler eventHandler;

    @StreamListener(Channels.ARRIVED)
    void consumeArrivedEvent(String vesselId) {
        //eventHandler.
    }
}
