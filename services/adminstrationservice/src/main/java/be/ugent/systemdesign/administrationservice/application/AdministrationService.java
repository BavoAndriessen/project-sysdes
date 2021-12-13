package be.ugent.systemdesign.administrationservice.application;

import be.ugent.systemdesign.administrationservice.application.command.OfferCreatedResponse;
import be.ugent.systemdesign.administrationservice.domain.Container;
import be.ugent.systemdesign.administrationservice.domain.Staff;

import java.time.LocalDateTime;
import java.util.List;

public interface AdministrationService {
    public OfferCreatedResponse registerNewVesselWithOffer(String vesselId, LocalDateTime arrivalDate, Integer lengthOfStay, Double vesselSize, Double amountOfWaste, List<Container> containerList);
    public Response registerNewTimeBadge(Integer staffId, LocalDateTime time);
    public Response handleOfferDeleted(String vesselId, Integer offerId);

    //for testing only
    public void addStaff(Staff s);
}
