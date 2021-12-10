package com.projectsysdes.containermanagement.API.REST;


import com.projectsysdes.containermanagement.application.event.EventListener;
import com.projectsysdes.containermanagement.application.query.ContainerQuery;
import com.projectsysdes.containermanagement.domain.ContainerLocation;
import com.projectsysdes.containermanagement.domain.ContainerLocationType;
import com.projectsysdes.containermanagement.domain.ContainerState;
import com.projectsysdes.containermanagement.domain.commands.TransferContainersCommand;
import com.projectsysdes.containermanagement.domain.events.ArrivedWithContainersEvent;
import com.projectsysdes.containermanagement.domain.events.ContainerApprovedEvent;
import com.projectsysdes.containermanagement.domain.events.ContainerScannedEvent;
import com.projectsysdes.containermanagement.domain.events.ReadyForContainersEvent;
import com.projectsysdes.containermanagement.infrastructure.ContainerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/containers")
public class ContainerRESTController {

    @Autowired
    private EventListener eventListener;

    @Autowired
    private ContainerQuery containerQuery;

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
    public ResponseEntity<List<TransferContainersCommand>> getTransferCommands() {
        return new ResponseEntity<>(containerQuery.findAllTransferCommands(), HttpStatus.OK);
    }


    @PostMapping("/approve")
    public void approveContainer(@RequestBody ContainerApprovedEvent event) {
        eventListener.consumeContainerApprovedEvent(event);
    }

    @PostMapping("/scan")
    public void scanContainer(@RequestBody String event) {
        System.out.println("=========================" + event + "====================");;
//        eventListener.consumeContainerScannedEvent(event);

    }
    @GetMapping("/scan")
    public ResponseEntity<ContainerScannedEvent> scanContainer() {
        return new ResponseEntity<>(new ContainerScannedEvent(0, ContainerState.ARRIVED, new ContainerLocation(ContainerLocationType.SHIP, "303")), HttpStatus.OK);
    }

    @PostMapping("/arrived")
    public void arrivedWithContainers(@RequestBody ArrivedWithContainersEvent event) {

        eventListener.consumeArrivedWithContainersEvent(event);
    }

    @PostMapping("/ready")
    public void readyForContainers(@RequestBody ReadyForContainersEvent event) {
        eventListener.consumeReadyForContainersEvent(event);
    }

//    private ResponseEntity<String> generateResponseEntity(ResponseStatus status, String sucessMessage, HttpStatus successHTTPStatus, String failMessage, HttpStatus failHTTPStatus) {
//        if (status == ResponseStatus.FAIL) {
//            return new ResponseEntity<>(failMessage, failHTTPStatus);
//        }
//        return new ResponseEntity<>(sucessMessage, successHTTPStatus);
//    }
}
