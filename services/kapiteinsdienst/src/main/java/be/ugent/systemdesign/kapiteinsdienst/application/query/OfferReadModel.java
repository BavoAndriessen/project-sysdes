package be.ugent.systemdesign.kapiteinsdienst.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OfferReadModel {
    private String vesselId;
    private Integer offerId;
    private Double price;
}
