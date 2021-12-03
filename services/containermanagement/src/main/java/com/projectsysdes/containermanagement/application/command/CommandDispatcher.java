package com.projectsysdes.containermanagement.application.command;

import com.projectsysdes.containermanagement.application.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandDispatcher {
    @Autowired
    ContainerService service;


}
