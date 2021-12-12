package be.ugent.systemdesign.administrationservice.application.command;

import lombok.Getter;

@Getter
public class DeleteOfferCommand {
    private String vesselId;
    private Integer offerId;
}
