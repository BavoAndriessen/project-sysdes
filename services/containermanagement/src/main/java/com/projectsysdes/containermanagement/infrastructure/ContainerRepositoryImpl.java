package com.projectsysdes.containermanagement.infrastructure;

import com.projectsysdes.containermanagement.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContainerRepositoryImpl implements ContainerRepository {

    @Autowired
    ContainerDataModelRepository containerDataModelRepository;


    @Override
    public Container find(int containerId) {
        return toContainer(containerDataModelRepository.findById(containerId).orElseThrow(ContainerNotFoundException::new));
    }

    @Override
    public void save(Container c) {
        containerDataModelRepository.save(toContainerDataModel(c));
    }

    @Override
    public List<Container> findAll() {
        List<Container> containers = new ArrayList<Container>();
        List<ContainerDataModel> l = containerDataModelRepository.findAll();
        for (ContainerDataModel c: l) {
            containers.add(toContainer(c));
        }
        return containers;
    }

    private Container toContainer(ContainerDataModel cdm) {
        return Container.builder()
                .containerId(cdm.getContainerId())
                .contents(cdm.getContents())
                .state(ContainerState.valueOf(cdm.getState()))
                .currentLocation(new ContainerLocation(ContainerLocationType.valueOf(cdm.getCurrentLocationType()), cdm.getCurrentLocationIdentifier()))
                .destinationLocation(new ContainerLocation(ContainerLocationType.valueOf(cdm.getDestinationLocationType()), cdm.getDestinationLocationIdentifier()))
                .build();
    }

    private ContainerDataModel toContainerDataModel(Container c) {
        return ContainerDataModel.builder()
                .containerId(c.getContainerId())
                .contents(c.getContents())
                .state(c.getState().name())
                .currentLocationType(c.getCurrentLocation().getLocationType().name())
                .currentLocationIdentifier(c.getCurrentLocation().getLocationIdentifier())
                .destinationLocationIdentifier(c.getDestinationLocation().getLocationIdentifier())
                .destinationLocationType(c.getDestinationLocation().getLocationType().name())
                .build();
    }
}
