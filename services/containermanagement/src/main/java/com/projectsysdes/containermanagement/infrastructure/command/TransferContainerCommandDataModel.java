package com.projectsysdes.containermanagement.infrastructure.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferContainerCommandDataModel extends DomainCommandDataModel {

    @Column(unique = true)
    private Integer containerId;
}
