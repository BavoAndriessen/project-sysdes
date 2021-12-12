package be.ugent.systemdesign.kapiteinsdienst.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteOfferCommand {

    private String vesselId;
    private Integer offerId;

    public DeleteOfferCommand(String vesselId){
        this.vesselId = vesselId;
        this.offerId = null;
    }

}
