package be.ugent.systemdesign.administrationservice.application.command;

import be.ugent.systemdesign.administrationservice.application.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler {

    @Autowired
    AdministrationService administrationService;

    public OfferCreatedResponse requestNewOfferForVessel(RequestOfferCommand command){
        return administrationService.registerNewVesselWithOffer(
                command.getVesselId(),
                command.getArrivalDateTime(),
                command.getLengthOfStay(),
                command.getVesselSize(),
                command.getAmountOfWaste(),
                command.getContainerList()
        );
    }

    public void requestToDeleteOffer(DeleteOfferCommand command){
        administrationService.handleOfferDeleted(command.getVesselId(), command.getOfferId());
    }
}
