package be.ugent.systemdesign.kapiteinsdienst.infrastructure;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDataModel {

    @Id
    private Integer containerId;
    private String contents;
}
