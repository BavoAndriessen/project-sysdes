package be.ugent.systemdesign.vesseltrafficcontrol.domain.events;

import lombok.Getter;

import java.time.LocalDateTime;

public class DomainEvent {

    @Getter
    private final LocalDateTime createdAt;

    public DomainEvent() { this.createdAt = LocalDateTime.now();}
}
