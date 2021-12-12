package be.ugent.systemdesign.administrationservice.infrastructure.document;

import be.ugent.systemdesign.administrationservice.domain.*;
import be.ugent.systemdesign.administrationservice.domain.seedwork.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    @Autowired
    DocumentDataModelRepository repository;

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public Document findOne(Integer id) {
        return mapToDocument(repository.findById(id).orElseThrow(DocumentNotFoundException::new));
    }

    @Override
    public void save(Document _d) {
        repository.save(mapToDocumentDataModel(_d));

        for(DomainEvent event : _d.getDomainEvents()){
            publisher.publishEvent(event);
        }
        _d.clearDomainEvents();
    }

    @Override
    public List<Document> findAllDocumentsForVessel(String vesselId) {
        return repository.findByVesselId(vesselId).stream()
                .map(d -> mapToDocument(d))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Document _d) {
        repository.deleteById(_d.getDocumentId());
    }

    private DocumentDataModel mapToDocumentDataModel(Document _document){
        return new DocumentDataModel(
                _document.getDocumentId(),
                _document.getVessel(),
                _document.getOffer(),
                _document.getContainerList()
        );
    }

    private Document mapToDocument(DocumentDataModel _documentDataModel){
        return Document.builder()
                .documentId(_documentDataModel.getDocumentId())
                .vessel(Vessel.builder()
                        .vesselId(_documentDataModel.getVesselId())
                        .arrivalDate(_documentDataModel.getArrivalDate())
                        .build()
                )
                .offer(Offer.builder()
                        .offerId(_documentDataModel.getOffer().getOfferId())
                        .price(_documentDataModel.getOffer().getPrice())
                        .lengthOfStay(_documentDataModel.getOffer().getLengthOfStay())
                        .arrivalTime(_documentDataModel.getOffer().getArrivalTime())
                        .vesselSize(_documentDataModel.getOffer().getVesselSize())
                        .amountOfWaste(_documentDataModel.getOffer().getAmountOfWaste())
                        .build()
                )
                .containerList(_documentDataModel.getContainerList().stream()
                        .map(c -> Container.builder()
                                .containerId(c.getContainerId())
                                .contents(c.getContents())
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }
}
