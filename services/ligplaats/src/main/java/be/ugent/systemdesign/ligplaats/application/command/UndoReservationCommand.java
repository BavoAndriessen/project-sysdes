

package be.ugent.systemdesign.ligplaats.application.command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UndoReservationCommand {
    //private Integer berthId;
    private String vesselId;
}
