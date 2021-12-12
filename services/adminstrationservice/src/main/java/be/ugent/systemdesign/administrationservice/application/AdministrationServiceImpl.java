package be.ugent.systemdesign.administrationservice.application;

import be.ugent.systemdesign.administrationservice.application.command.OfferCreatedResponse;
import be.ugent.systemdesign.administrationservice.domain.*;
import be.ugent.systemdesign.administrationservice.infrastructure.staff.StaffNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    StaffRepository staffRepository;

    @Override
    public OfferCreatedResponse registerNewVesselWithOffer(String vesselId, LocalDateTime arrivalDate, Integer lengthOfStay, Double vesselSize, Double amountOfWaste, List<Container> containerList) {
        Double price = Document.calculatePrice(lengthOfStay, vesselSize, amountOfWaste);
        Integer offerId = Offer.getNewId();
        Offer offer = new Offer(offerId, price, lengthOfStay, arrivalDate, vesselSize, amountOfWaste);
        Vessel vessel = new Vessel(vesselId, arrivalDate);
        Document document = new Document(Document.getNewId(), vessel, offer, containerList);

        documentRepository.save(document);

        return new OfferCreatedResponse(ResponseStatus.SUCCES, "new offer "+offerId+" for vessel " + vesselId + " registered",
                offerId, vessel.getVesselId(), price);
    }

    @Override
    public Response registerNewTimeBadge(Integer staffId, LocalDateTime time) {
        try {
            Staff s = staffRepository.findOne(staffId);
            s.addWorkedHour(time);
            return new Response(ResponseStatus.SUCCES, "staff " + staffId + " has badged on " + time);
        }
        catch(StaffNotFoundException e){
            return new Response(ResponseStatus.FAIL, "staff " + staffId + " is not found");
        }
    }

    @Override
    public Response handleOfferDeleted(String vesselId, Integer offerId) {
        List<Document> documents = documentRepository.findAllDocumentsForVessel(vesselId);
        if(documents.size() == 0){
            return new Response(ResponseStatus.FAIL, "The  vessel " + vesselId + " is not found");
        }
        for(Document d : documents){
            if(d.getOffer().getOfferId() == offerId){
                documentRepository.delete(d);
                return new Response(ResponseStatus.SUCCES, "The offer "+ offerId + " has been deleted");
            }
        }
        return new Response(ResponseStatus.FAIL, "The offer " + offerId + " of vessel " + vesselId + " is not found");
    }

    //for testing only

    @Override
    public void addStaff(Staff s) {
        staffRepository.save(s);
    }
}
