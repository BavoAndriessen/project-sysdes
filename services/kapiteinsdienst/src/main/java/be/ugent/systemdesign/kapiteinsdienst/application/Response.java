package be.ugent.systemdesign.kapiteinsdienst.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {

    public ResponseStatus status;
    public String message;


}
