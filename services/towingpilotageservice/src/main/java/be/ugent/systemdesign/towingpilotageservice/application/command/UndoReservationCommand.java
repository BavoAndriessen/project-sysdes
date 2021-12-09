package be.ugent.systemdesign.towingpilotageservice.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UndoReservationCommand {
    private String vesselId;

    public UndoReservationCommand(String vesselId) {
        this.vesselId = vesselId;
    }
}
