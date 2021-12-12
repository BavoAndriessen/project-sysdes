package be.ugent.systemdesign.administrationservice.application.event;

import be.ugent.systemdesign.administrationservice.domain.NewContainerListEvent;

public interface EventDispatcher {
    public void publishNewContainerListEvent(NewContainerListEvent event);
}
