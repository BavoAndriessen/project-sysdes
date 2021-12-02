package be.ugent.systemdesign.kapiteinsdienst.application.command.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferConfirmationCommand {

    private Integer vesselNumber;
    private Boolean confirmed;
}
