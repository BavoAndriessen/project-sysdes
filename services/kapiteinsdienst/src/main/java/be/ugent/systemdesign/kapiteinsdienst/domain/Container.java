package be.ugent.systemdesign.kapiteinsdienst.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//TODO enkel voor test behouden
@AllArgsConstructor
public class Container {

    private Integer containerId;
    private String contents;
}
