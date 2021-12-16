package be.ugent.systemdesign.vesseltrafficcontrol.api.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    // output channel
    static final String NAVIGATE_SHIP = "navigate_ship";
    static final String SHIP_ARRIVING = "ship_arriving";

    @Output(NAVIGATE_SHIP)
    MessageChannel navigateShip();

    @Output(SHIP_ARRIVING)
    MessageChannel shipArriving();

    // input channel
    static final String ARRIVED = "arrived";
    static final String DOCK_READY = "dock_ready";
    static final String SHIP_READY = "ship_ready";

    @Input(ARRIVED)
    MessageChannel arrived();

    @Input(DOCK_READY)
    MessageChannel dockReady();

    @Input(SHIP_READY)
    MessageChannel shipReady();
}
