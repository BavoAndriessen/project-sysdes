package be.ugent.systemdesign.maintenanceservice.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    public ResponseStatus status;
    public String message;


}
