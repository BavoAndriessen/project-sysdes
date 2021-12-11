package com.projectsysdes.containermanagement.domain;

import com.projectsysdes.containermanagement.infrastructure.TransferContainerCommandDataModel;

import java.util.Collection;
import java.util.List;

public interface ContainerRepository {
    Container find(int containerId);
    void save(Container c);
    void save(Collection<Container> containers);
    List<Container> findAll();
    List<Container> findContainersWithContainerIds(Collection<Integer> containerIds);
    List<TransferContainerCommandDataModel> findAllTransferCommands();
}
