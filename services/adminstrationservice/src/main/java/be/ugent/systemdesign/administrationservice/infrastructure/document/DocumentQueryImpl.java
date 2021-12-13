package be.ugent.systemdesign.administrationservice.infrastructure.document;

import be.ugent.systemdesign.administrationservice.application.query.DocumentQuery;
import be.ugent.systemdesign.administrationservice.application.query.DocumentReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentQueryImpl implements DocumentQuery {

    @Autowired
    private DocumentDataModelRepository repository;

    @Override
    public List<DocumentReadModel> generateDocumentsForVessel(String vesselId) {
        return repository.findByVesselId(vesselId).stream()
                .map(documentDataModel -> mapToDocumentReadModel(documentDataModel))
                .collect(Collectors.toList());
    }

    private DocumentReadModel mapToDocumentReadModel(DocumentDataModel documentDataModel){
        return new DocumentReadModel(documentDataModel.getDocumentId(), documentDataModel.getVesselId(), documentDataModel.getContainerList());
    }
}
