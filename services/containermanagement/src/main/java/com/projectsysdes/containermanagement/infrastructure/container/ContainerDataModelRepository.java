package com.projectsysdes.containermanagement.infrastructure.container;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ContainerDataModelRepository extends JpaRepository<ContainerDataModel, Integer> {
    List<ContainerDataModel> findByContentsLike(String contentsQueryString);
    List<ContainerDataModel> findByContainerIdIn(Collection<Integer> containerIds);
    List<ContainerDataModel> findByReadyForContainersRequestDataModel(ReadyForContainersRequestDataModel readyForContainersRequestDataModel);
}
