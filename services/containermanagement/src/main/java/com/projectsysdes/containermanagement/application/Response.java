package com.projectsysdes.containermanagement.application;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Response {
    private final String message;
    private final ResponseStatus status;
}
