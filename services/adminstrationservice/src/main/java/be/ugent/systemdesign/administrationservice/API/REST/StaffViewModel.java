package be.ugent.systemdesign.administrationservice.API.REST;

import be.ugent.systemdesign.administrationservice.application.query.StaffReadModel;
import be.ugent.systemdesign.administrationservice.domain.StaffType;
import lombok.Getter;

@Getter
public class StaffViewModel {
    private String staffId;
    private String lastName;
    private String firstName;
    private String type;
    private String wage;
    private String hoursWorked;
    private String pay;

    public StaffViewModel(StaffReadModel staffReadModel){
        this.staffId = staffReadModel.getStaffId().toString();
        this.lastName = staffReadModel.getLastName();
        this.firstName = staffReadModel.getFirstName();
        this.type = staffReadModel.getType().toString();
        this.wage = staffReadModel.getWage().toString();
        this.hoursWorked = staffReadModel.getHoursWorked().toString();
        this.pay = staffReadModel.getPay().toString();
    }
}
