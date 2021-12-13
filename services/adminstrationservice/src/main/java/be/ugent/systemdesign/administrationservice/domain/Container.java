package be.ugent.systemdesign.administrationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Container {
    private Integer containerId;
    private String contents;
}
