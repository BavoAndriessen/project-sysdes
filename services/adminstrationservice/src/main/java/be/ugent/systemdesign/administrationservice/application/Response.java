package be.ugent.systemdesign.administrationservice.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {
    private ResponseStatus status;
    private String message;
}
