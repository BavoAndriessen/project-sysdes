package be.ugent.systemdesign.administrationservice.infrastructure.staff;

import be.ugent.systemdesign.administrationservice.domain.StaffType;
import be.ugent.systemdesign.administrationservice.domain.WorkedHours;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StaffDataModel {
    @Id
    private Integer staffId;
    private String lastName;
    private String firstName;
    private StaffType type;
    private Double wage;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WorkedHoursDataModel> workedHoursList;

    public StaffDataModel(Integer staffId, String lastName, String firstName, StaffType type, Double wage,
                          List<WorkedHours> workedHoursList){
        this.staffId = staffId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.type = type;
        this.wage = wage;
        if(workedHoursList != null){
            this.workedHoursList = workedHoursList.stream().map(e -> new WorkedHoursDataModel(e.getStart(), e.getStop())).collect(Collectors.toList());
        }
        else{
            this.workedHoursList = null;
        }
    }
}
