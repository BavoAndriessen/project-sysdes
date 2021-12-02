package com.projectsysdes.containermanagement.domain;

import java.util.List;

public interface ContainerRepository {
    Container find(int containerId);
    void save(Container c);
    List<Container> findAll();

}
