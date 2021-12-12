package com.projectsysdes.containermanagement.domain.container;

import java.util.Collection;
import java.util.List;

public interface ContainerRepository {
    Container find(int containerId);
    void save(Container c);
    void save(Collection<Container> containers);
    List<Container> findAll();
    List<Container> findContainersWithContainerIds(Collection<Integer> containerIds);
}
