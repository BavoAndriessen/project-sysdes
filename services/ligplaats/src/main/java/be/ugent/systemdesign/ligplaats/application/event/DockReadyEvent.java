package be.ugent.systemdesign.ligplaats.application.event;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DockReadyEvent extends DomainEvent {
    private Integer berthId;
    private int berthNumber;
    public DockReadyEvent(int berthId, int berthNumber){
        super();
        this.berthId = berthId;
        this.berthNumber = berthNumber;
    }
}
