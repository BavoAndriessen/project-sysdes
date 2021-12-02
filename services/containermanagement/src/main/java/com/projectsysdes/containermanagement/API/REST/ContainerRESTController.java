package com.projectsysdes.containermanagement.API.REST;


import com.projectsysdes.containermanagement.application.ContainerService;
import com.projectsysdes.containermanagement.application.query.ContainerQuery;
import com.projectsysdes.containermanagement.infrastructure.ContainerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/containers")
public class ContainerRESTController {

//    @Autowired
//    private ContainerService service;

    @Autowired
    private ContainerQuery query;

    @GetMapping("/{id}/location")
    public ResponseEntity<ContainerLocationViewModel> getContainerLocation(@PathVariable("id") String containerId) {
        try {
            Integer id= Integer.parseInt(containerId);
            return new ResponseEntity<>(query.getContainerLocation(id), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ContainerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<ContainerViewModel> searchContainersForContents(@RequestParam(name="contents") String contents) {
        return query.searchContainersForContents(contents);
    }

//    private ResponseEntity<String> generateResponseEntity(ResponseStatus status, String sucessMessage, HttpStatus successHTTPStatus, String failMessage, HttpStatus failHTTPStatus) {
//        if (status == ResponseStatus.FAIL) {
//            return new ResponseEntity<>(failMessage, failHTTPStatus);
//        }
//        return new ResponseEntity<>(sucessMessage, successHTTPStatus);
//    }
}
