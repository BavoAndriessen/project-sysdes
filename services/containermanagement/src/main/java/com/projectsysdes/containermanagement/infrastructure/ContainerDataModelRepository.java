package com.projectsysdes.containermanagement.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContainerDataModelRepository extends JpaRepository<ContainerDataModel, Integer> {
    List<ContainerDataModel> findByContentsLike(String contentsQueryString);
}
