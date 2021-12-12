package be.ugent.systemdesign.administrationservice.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    static final String NEW_CONTAINER_LIST = "new_containers";
    static final String REQUEST_OFFER = "request_offer";
    static final String OFFER_CREATED = "offer_created";
    static final String DELETE_OFFER = "delete_offer";

    @Output(NEW_CONTAINER_LIST)
    MessageChannel newContainerList();

    @Input(REQUEST_OFFER)
    SubscribableChannel requestOffer();

    @Output(OFFER_CREATED)
    MessageChannel offerCreated();

    @Input(DELETE_OFFER)
    SubscribableChannel deleteOffer();
}
