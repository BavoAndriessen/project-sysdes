package be.ugent.systemdesign.kapiteinsdienst.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrewDataModel {

    @Id
    private Integer crewId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String type;
}
