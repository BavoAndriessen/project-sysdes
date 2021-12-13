package be.ugent.systemdesign.ligplaats.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReserveBerthCommand {
    private String vesselId;
    private Double vesselSize;

}
