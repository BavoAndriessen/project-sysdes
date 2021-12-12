package be.ugent.systemdesign.administrationservice.application.query;

import be.ugent.systemdesign.administrationservice.domain.StaffType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StaffReadModel {
    private Integer staffId;
    private String lastName;
    private String firstName;
    private StaffType type;
    private Double wage;
    private Double hoursWorked;
    private Double pay;
}
