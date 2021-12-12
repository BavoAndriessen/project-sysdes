package be.ugent.systemdesign.administrationservice.application.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteOfferCommand {
    private String vesselId;
    private Integer offerId;
}
