package be.ugent.systemdesign.administrationservice.domain;

import be.ugent.systemdesign.administrationservice.domain.seedwork.DomainEvent;
import lombok.Getter;

import java.util.List;

@Getter
public class NewContainerListEvent extends DomainEvent {
    private List<Container> containerList;

    public NewContainerListEvent(List<Container> containerList){
        super();
        this.containerList = containerList;
    }
}
