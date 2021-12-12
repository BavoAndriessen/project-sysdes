package be.ugent.systemdesign.administrationservice.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private ResponseStatus status;
    private String message;
}
