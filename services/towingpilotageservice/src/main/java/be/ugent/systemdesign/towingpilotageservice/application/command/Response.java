package be.ugent.systemdesign.towingpilotageservice.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {

    public ResponseStatus status;
    public String message;


}
