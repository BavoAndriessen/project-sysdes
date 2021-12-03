package com.projectsysdes.containermanagement.application;

import com.projectsysdes.containermanagement.API.REST.ContainerLocationViewModel;
import com.projectsysdes.containermanagement.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    ContainerRepository repo;


    @Override
    public Response approveContainer(Integer containerId) {
        try {
            Container c = repo.find(containerId);
            c.progressState(ContainerState.TRANSIT_APPROVED, null);
            repo.save(c);
        } catch (IllegalContainerStateChangeException e) {
            return new Response("Illegal state change: container with id " + containerId.toString() + " is not in transit or is already approved", ResponseStatus.FAIL);
        } catch (RuntimeException e) {
            return new Response("Could not approve container with id " + containerId.toString(), ResponseStatus.FAIL);
        }
        return new Response("Approved container with id: " + containerId.toString(), ResponseStatus.SUCCESS);
    }

    @Override
    public Response arrivedWithContainer(List<Integer> containers, ContainerLocationViewModel at) {
        return null;
    }

    @Override
    public Response readyForContainers(ContainerLocationViewModel location) {
        return null;
    }

    @Override
    public Response scanContainer(Integer containerId, String state, ContainerLocationViewModel location) {
        try {
            Container c = repo.find(containerId);
            c.progressState(ContainerState.valueOf(state), toContainerLocationViewModel(location));
            repo.save(c);
        } catch (IllegalContainerStateChangeException e) {
            return new Response("Illegal state change: state of container with id " + containerId.toString() + " cannot be changed to " + state, ResponseStatus.FAIL);
        } catch (RuntimeException e) {
            return new Response("Could not update the state of container with id " + containerId.toString(), ResponseStatus.FAIL);
        }
        return new Response("Updated container with id: " + containerId.toString() + "to state " + state, ResponseStatus.SUCCESS);
    }

    private ContainerLocation toContainerLocationViewModel(ContainerLocationViewModel clvm) {
        return new ContainerLocation(ContainerLocationType.valueOf(clvm.getLocationType()), clvm.getLocationIdentifier());
    }


}
