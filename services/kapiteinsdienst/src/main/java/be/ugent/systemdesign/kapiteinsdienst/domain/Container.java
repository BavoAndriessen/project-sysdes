package be.ugent.systemdesign.kapiteinsdienst.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Container {

    private Integer containerId;
    private String contents;

    public Container(String contents){
        this.contents = contents;
    }
}
