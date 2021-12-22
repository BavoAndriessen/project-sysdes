package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeGateStateCommandDataModel extends DomainCommandDataModel{

    @Column
    private Integer gateId;
}
