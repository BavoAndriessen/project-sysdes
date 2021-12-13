package be.ugent.systemdesign.administrationservice.application.query;

import be.ugent.systemdesign.administrationservice.infrastructure.document.ContainerDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DocumentReadModel {
    private Integer documentId;
    private String vesselId;
    private List<ContainerDataModel> containerList; //Zou ook kunnen dat dit gwn Container moet zijn
}
