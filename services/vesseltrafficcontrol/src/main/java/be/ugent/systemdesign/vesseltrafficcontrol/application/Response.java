package be.ugent.systemdesign.vesseltrafficcontrol.application;

public class Response {
    final public String message;
    final public ResponseStatus status;
    public Response(ResponseStatus status, String message){ this.status = status; this.message = message;}
}
