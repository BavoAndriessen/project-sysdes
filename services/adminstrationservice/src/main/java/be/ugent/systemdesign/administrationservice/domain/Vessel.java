package be.ugent.systemdesign.administrationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class Vessel {
    private String vesselId;
    private LocalDateTime arrivalDate;
}
