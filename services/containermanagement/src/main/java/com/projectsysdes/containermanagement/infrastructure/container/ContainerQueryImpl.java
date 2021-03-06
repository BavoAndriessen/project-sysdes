package com.projectsysdes.containermanagement.infrastructure.container;

import com.projectsysdes.containermanagement.API.REST.ContainerViewModel;
import com.projectsysdes.containermanagement.application.query.ContainerQuery;
import com.projectsysdes.containermanagement.domain.container.ContainerLocation;
import com.projectsysdes.containermanagement.domain.container.ContainerLocationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContainerQueryImpl implements ContainerQuery {

    @Autowired
    ContainerDataModelRepository repo;

    @Override
    public ContainerLocation getContainerLocation(Integer containerId) throws ContainerNotFoundException {
        ContainerDataModel cdm = repo.findById(containerId).orElseThrow(ContainerNotFoundException::new);
        return new ContainerLocation(ContainerLocationType.valueOf(cdm.getCurrentLocationType()), cdm.getCurrentLocationIdentifier());
    }

    @Override
    public List<ContainerViewModel> searchContainersForContents(String contents) {
        return repo.findByContentsLike("%"+contents+"%").stream()
                .map(c -> {
                    return new ContainerViewModel(c.getContainerId(), c.getContents(), c.getState(), new ContainerLocation(ContainerLocationType.valueOf(c.getCurrentLocationType()), c.getCurrentLocationIdentifier()), new ContainerLocation(ContainerLocationType.valueOf(c.getDestinationLocationType()), c.getDestinationLocationIdentifier()));
                })
                .collect(Collectors.toList());
    }

}
