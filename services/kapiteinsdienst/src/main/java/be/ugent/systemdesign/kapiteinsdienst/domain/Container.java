package be.ugent.systemdesign.kapiteinsdienst.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Container {

    private Integer containerId;
    private String contents;
}
