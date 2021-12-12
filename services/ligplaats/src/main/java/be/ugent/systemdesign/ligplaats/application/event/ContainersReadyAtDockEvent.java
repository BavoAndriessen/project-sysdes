package be.ugent.systemdesign.ligplaats.application.event;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContainersReadyAtDockEvent extends DomainEvent {
    private Integer berthId;

    public ContainersReadyAtDockEvent(Integer id){
        super();
        this.berthId = id;
    }

}
