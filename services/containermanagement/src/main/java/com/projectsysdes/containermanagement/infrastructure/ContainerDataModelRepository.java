package com.projectsysdes.containermanagement.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerDataModelRepository extends JpaRepository<ContainerDataModel, Integer> {
    // list<foo> findBy...()
}
