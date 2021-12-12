package be.ugent.systemdesign.administrationservice.domain.seedwork;

import lombok.Getter;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    @Getter
    private final LocalDateTime createdAt;

    public DomainEvent(){
        this.createdAt = LocalDateTime.now();
    }
}
