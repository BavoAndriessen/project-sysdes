package be.ugent.systemdesign.kapiteinsdienst.infrastructure;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer containerId;
    private String contents;

    public ContainerDataModel(String contents){
        this.contents = contents;
    }
}
