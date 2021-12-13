package be.ugent.systemdesign.administrationservice.infrastructure.staff;

import be.ugent.systemdesign.administrationservice.application.query.StaffQuery;
import be.ugent.systemdesign.administrationservice.application.query.StaffReadModel;
import be.ugent.systemdesign.administrationservice.domain.Staff;
import be.ugent.systemdesign.administrationservice.domain.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffQueryImpl implements StaffQuery {

    @Autowired
    private StaffRepository repository;

    @Override
    public StaffReadModel generatePayCheck(Integer staffId) {
        Staff s = repository.findOne(staffId);
        StaffReadModel staffReadModel = mapToStaffReadModel(s);
        //staff needs to be saved because working hours will be changed
        repository.save(s);
        return staffReadModel;
    }

    private StaffReadModel mapToStaffReadModel(Staff staff){
        return new StaffReadModel(
                staff.getStaffId(),
                staff.getLastName(),
                staff.getFirstName(),
                staff.getType(),
                staff.getWage(),
                staff.hoursWorked(),
                staff.getPay()
        );
    }
}
