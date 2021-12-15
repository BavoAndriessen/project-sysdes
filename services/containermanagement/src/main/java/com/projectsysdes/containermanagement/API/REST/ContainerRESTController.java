package com.projectsysdes.containermanagement.API.REST;


import com.projectsysdes.containermanagement.application.Response;
import com.projectsysdes.containermanagement.application.ResponseStatus;
import com.projectsysdes.containermanagement.application.event.EventHandler;
import com.projectsysdes.containermanagement.application.query.CommandQuery;
import com.projectsysdes.containermanagement.application.query.ContainerQuery;
import com.projectsysdes.containermanagement.domain.container.ContainerLocation;
import com.projectsysdes.containermanagement.domain.command.TransferContainerCommand;
import com.projectsysdes.containermanagement.domain.events.ArrivedWithContainersEvent;
import com.projectsysdes.containermanagement.domain.events.ContainerApprovedEvent;
import com.projectsysdes.containermanagement.domain.events.ContainerScannedEvent;
import com.projectsysdes.containermanagement.domain.events.ReadyForContainersEvent;
import com.projectsysdes.containermanagement.infrastructure.container.ContainerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/containers")
@CrossOrigin("*")
public class ContainerRESTController {

    @Autowired
    private EventHandler eventHandler;

    @Autowired
    private ContainerQuery containerQuery;

    @Autowired
    private CommandQuery commandQuery;

    @GetMapping("/{id}/location")
    public ResponseEntity<ContainerLocation> getContainerLocation(@PathVariable("id") String containerId) {
        try {
            Integer id= Integer.parseInt(containerId);
            return new ResponseEntity<>(containerQuery.getContainerLocation(id), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ContainerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<ContainerViewModel> searchContainersForContents(@RequestParam(name="contents") String contents) {
        return containerQuery.searchContainersForContents(contents);
    }

    @GetMapping("/commands/transfer")
    public ResponseEntity<List<TransferContainerCommand>> getTransferCommands() {
        return new ResponseEntity<>(commandQuery.findAllTransferContainerCommands(), HttpStatus.OK);
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approveContainer(@RequestBody ContainerApprovedEvent event) {
        Response r = eventHandler.consumeContainerApprovedEvent(event);
        return generateResponseEntity(r, HttpStatus.ACCEPTED, HttpStatus.CONFLICT);
    }

    @PostMapping("/scan")
    public ResponseEntity<String> scanContainer(@RequestBody ContainerScannedEvent event) {
        Response r = eventHandler.consumeContainerScannedEvent(event);
        return generateResponseEntity(r, HttpStatus.ACCEPTED, HttpStatus.CONFLICT);
    }

    @PostMapping("/arrived")
    public ResponseEntity<String> arrivedWithContainers(@RequestBody ArrivedWithContainersEvent event) {
        Response r = eventHandler.consumeArrivedWithContainersEvent(event);
        return generateResponseEntity(r, HttpStatus.ACCEPTED, HttpStatus.CONFLICT);
    }

    @PostMapping("/ready")
    public ResponseEntity<String> readyForContainers(@RequestBody ReadyForContainersEvent event) {
        Response r = eventHandler.consumeReadyForContainersEvent(event);
        return generateResponseEntity(r, HttpStatus.ACCEPTED, HttpStatus.CONFLICT);
    }

    private ResponseEntity<String> generateResponseEntity(Response r, HttpStatus successHTTPStatus, HttpStatus failHTTPStatus) {
        String message = r.getStatus() + ": " + r.getMessage();
        if (r.getStatus() == ResponseStatus.FAIL) {
            return new ResponseEntity<>(message, failHTTPStatus);
        }
        return new ResponseEntity<>(message, successHTTPStatus);
    }
}
