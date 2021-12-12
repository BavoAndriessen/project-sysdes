package be.ugent.systemdesign.administrationservice.infrastructure.staff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@NoArgsConstructor
@Embeddable
public class WorkedHoursDataModel {
    @Getter
    private LocalDateTime start;
    @Getter
    private LocalDateTime stop;

    public WorkedHoursDataModel(LocalDateTime start, LocalDateTime stop){
        this.start = start;
        this.stop = stop;
    }
}
