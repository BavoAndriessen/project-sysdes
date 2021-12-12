package be.ugent.systemdesign.ligplaats.application.event;

import lombok.Getter;

import java.time.LocalDateTime;

public abstract class DomainEvent {

    @Getter
    private final LocalDateTime createdTime;

    public DomainEvent(){
        this.createdTime = LocalDateTime.now();
    }


}
