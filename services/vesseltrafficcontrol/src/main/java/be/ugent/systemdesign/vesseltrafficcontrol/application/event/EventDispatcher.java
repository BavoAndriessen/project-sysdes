package be.ugent.systemdesign.vesseltrafficcontrol.application.event;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.DomainEvent;

public interface EventDispatcher {

    void publishEvent(DomainEvent event);

}
