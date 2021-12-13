package be.ugent.systemdesign.vesseltrafficcontrol.domain.command;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class DomainCommand {
    private final LocalDateTime createdTime;

    public DomainCommand(){
        this.createdTime = LocalDateTime.now();
    }
}
