package com.projectsysdes.containermanagement.infrastructure;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class TransferContainerCommandDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ElementCollection()
    private List<Integer> containerIds;

    private ContainerLocationDataModel toLocation;

}
