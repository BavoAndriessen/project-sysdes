package be.ugent.systemdesign.ligplaats.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BerthRealModel {
    private Integer berthId;
    private int berthNumber;
    private String vesselId;
}
