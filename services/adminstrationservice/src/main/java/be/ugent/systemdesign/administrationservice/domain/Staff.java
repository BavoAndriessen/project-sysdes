package be.ugent.systemdesign.administrationservice.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC) //TERUG VERANDEREN!!
public class Staff {
    private Integer staffId;
    private String lastName;
    private String firstName;
    private StaffType type;
    private Double wage;
    private List<WorkedHours> workedHoursList;

//    public void addWorkedHour2(LocalDateTime start, LocalDateTime stop){
//        if(this.workedHoursList == null){
//            this.workedHoursList = new ArrayList<>();
//        }
//        this.workedHoursList.add(new WorkedHours(start, stop));
//    }

    public void addWorkedHour(LocalDateTime time){
        if(this.workedHoursList == null){
            this.workedHoursList = new ArrayList<>();
            this.workedHoursList.add(new WorkedHours(time, null));
        }
        else if(this.workedHoursList.size() == 0){
            this.workedHoursList.add(new WorkedHours(time, null));
        }
        else if(this.workedHoursList.get(this.workedHoursList.size() - 1).getStop() == null){
            this.workedHoursList.get(this.workedHoursList.size() - 1).setStop(time);
        }
        else{
            this.workedHoursList.add(new WorkedHours(time, null));
        }
    }

    public Double hoursWorked() throws shiftNotOverExecption{
        if(this.workedHoursList.get(this.workedHoursList.size() - 1).getStop() == null){
            throw new shiftNotOverExecption();
        }
        //count all the worked hours
        double count = 0;
        for(WorkedHours wh : workedHoursList){
            count += ChronoUnit.HOURS.between(wh.getStart(), wh.getStop());
        }
        return count;
    }

    public Double getPay() throws shiftNotOverExecption{
        double hoursWorked = hoursWorked();
        //worked hours are deleted after pay
        this.workedHoursList = new ArrayList<>();
        return wage * hoursWorked;
    }
}
