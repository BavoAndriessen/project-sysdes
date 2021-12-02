package be.ugent.systemdesign.kapiteinsdienst.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Crew {

    private Integer crewId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String type;
}
