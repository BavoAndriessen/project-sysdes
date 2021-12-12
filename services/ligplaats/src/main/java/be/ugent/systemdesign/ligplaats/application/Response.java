package be.ugent.systemdesign.ligplaats.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Response {
    public String message;
    public ResponseStatus status;
    protected Response(ResponseStatus status, String message){ this.status = status; this.message = message;}
}
