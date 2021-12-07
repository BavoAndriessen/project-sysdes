package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UndoReservationCommand {
    private String vesselId;

    public UndoReservationCommand(String vesselId) {
        this.vesselId = vesselId;
    }
}
