package be.ugent.systemdesign.ligplaats.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReserveBerthCommand {
    private String vesselId;
    private Double vesselSize;

}
