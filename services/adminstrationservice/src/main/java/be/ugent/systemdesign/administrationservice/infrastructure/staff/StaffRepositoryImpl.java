package be.ugent.systemdesign.administrationservice.infrastructure.staff;

import be.ugent.systemdesign.administrationservice.domain.Staff;
import be.ugent.systemdesign.administrationservice.domain.StaffRepository;
import be.ugent.systemdesign.administrationservice.domain.WorkedHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;


@Repository
public class StaffRepositoryImpl implements StaffRepository {

    @Autowired
    StaffDataModelRepository repository;


    @Override
    public Staff findOne(Integer id) {
        return mapToStaff(repository.findById(id).orElseThrow(StaffNotFoundException::new));
    }

    @Override
    public void save(Staff _f) {
        repository.save(mapToStaffDataModel(_f));
    }

    private StaffDataModel mapToStaffDataModel(Staff _staff){
        return new StaffDataModel(
                _staff.getStaffId(),
                _staff.getLastName(),
                _staff.getFirstName(),
                _staff.getType(),
                _staff.getWage(),
                _staff.getWorkedHoursList()
        );
    }

    private Staff mapToStaff(StaffDataModel staffDataModel){
        return Staff.builder()
                .firstName(staffDataModel.getFirstName())
                .lastName(staffDataModel.getLastName())
                .staffId(staffDataModel.getStaffId())
                .type(staffDataModel.getType())
                .wage(staffDataModel.getWage())
                .workedHoursList(staffDataModel.getWorkedHoursList()
                        .stream()
                        .map(h -> WorkedHours.builder()
                                .start(h.getStart())
                                .stop(h.getStop())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

}
