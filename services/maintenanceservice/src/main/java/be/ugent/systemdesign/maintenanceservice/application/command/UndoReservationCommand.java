package be.ugent.systemdesign.maintenanceservice.application.command;

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
