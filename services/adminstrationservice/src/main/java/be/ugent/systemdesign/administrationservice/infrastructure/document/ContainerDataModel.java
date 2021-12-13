package be.ugent.systemdesign.administrationservice.infrastructure.document;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
@NoArgsConstructor
@Getter
public class ContainerDataModel {
//    @Id
    private Integer containerId;
    private String contents;

    public ContainerDataModel(Integer containerId, String contents){
        this.containerId = containerId;
        this.contents = contents;
    }
}
