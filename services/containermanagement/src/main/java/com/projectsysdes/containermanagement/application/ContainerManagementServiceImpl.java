package com.projectsysdes.containermanagement.application;

import com.projectsysdes.containermanagement.application.command.CommandDispatcher;
import com.projectsysdes.containermanagement.domain.*;
import com.projectsysdes.containermanagement.domain.commands.TransferContainersCommand;
import com.projectsysdes.containermanagement.domain.exceptions.IllegalContainerStateChangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ContainerManagementServiceImpl implements ContainerManagementService {

    @Autowired
    private ContainerRepository repo;
    @Autowired
    private CommandDispatcher commandDispatcher;

    @Override
    public Response approveContainer(Integer containerId) {
        try {
            Container c = repo.find(containerId);
            c.progressState(ContainerState.TRANSIT_APPROVED, null);
            if (c.isDestinationLocationReady()) {
                commandDispatcher.transferContainersCommand(new TransferContainersCommand(List.of(containerId), c.getDestinationLocation()));
            }
            repo.save(c);
        } catch (IllegalContainerStateChangeException e) {
            return new Response("Illegal state change: container with id " + containerId.toString() + " is not in transit or is already approved", ResponseStatus.FAIL);
        } catch (RuntimeException e) {
            return new Response("Could not approve container with id " + containerId.toString(), ResponseStatus.FAIL);
        }
        return new Response("Approved container with id: " + containerId.toString(), ResponseStatus.SUCCESS);
    }

    @Override
    public Response arrivedWithContainers(List<Integer> containerIds, ContainerLocation at) {
        List<Container> containers = repo.findContainersWithContainerIds(containerIds);
        for (Container c: containers) {
            try {
                c.progressState(ContainerState.ARRIVED, at);
            } catch (IllegalContainerStateChangeException e) {
                // container has already arrived => no further action required
            }
        }
        repo.save(containers);
        return new Response("Containers registered as arrived", ResponseStatus.SUCCESS);
    }

    @Override
    public Response readyForContainers(List<Integer> containerIds, ContainerLocation location) {
        List<Container> containers = repo.findContainersWithContainerIds(containerIds);
        List<Integer> containerIdsToBeTransported = new ArrayList<>();
        for (Container c: containers) {
            c.setDestinationLocationReady(location);
            if (c.getState() == ContainerState.TRANSIT_APPROVED) {
                containerIdsToBeTransported.add(c.getContainerId());
            }
        }
        repo.save(containers);
        commandDispatcher.transferContainersCommand(new TransferContainersCommand(containerIdsToBeTransported, location));
        return new Response("Containers will arrive as soon as possible", ResponseStatus.SUCCESS);
    }

    @Override
    public Response scanContainer(Integer containerId, String state, ContainerLocation location) {
        try {
            Container c = repo.find(containerId);
            try {
                c.progressState(ContainerState.valueOf(state), location);
                repo.save(c);
            } catch (IllegalContainerStateChangeException e) {
                return new Response("Illegal state change: state of container with id " + containerId.toString() + " cannot be changed to " + state + ", because it is " + c.getState().name(), ResponseStatus.FAIL);
            }
        }  catch (RuntimeException e) {
            return new Response("Could not update the state of container with id " + containerId.toString(), ResponseStatus.FAIL);
        }
        return new Response("Updated container with id: " + containerId.toString() + "to state " + state, ResponseStatus.SUCCESS);
    }

}
