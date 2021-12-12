package be.ugent.systemdesign.administrationservice.API.REST;

import be.ugent.systemdesign.administrationservice.application.query.DocumentReadModel;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DocumentViewModel {
    private String documentId;
    private String vesselId;
    private List<String> containers;

    public DocumentViewModel(DocumentReadModel document){
        this.documentId = document.getDocumentId().toString();
        this.vesselId = document.getVesselId();
        this.containers = document.getContainerList()
                .stream()
                .map(c -> "container "+ c.getContainerId().toString()+ " with contents: " + c.getContents())
                .collect(Collectors.toList());
    }
}
