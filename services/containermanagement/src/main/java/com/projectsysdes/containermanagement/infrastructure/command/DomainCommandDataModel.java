package com.projectsysdes.containermanagement.infrastructure.command;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DomainCommandDataModel {
    @Id
    @GeneratedValue
    protected Integer commandId;

    private LocalDateTime createdTime;
}
