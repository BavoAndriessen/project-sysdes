package be.ugent.systemdesign.administrationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class WorkedHours {
    private LocalDateTime start;
    @Setter
    private LocalDateTime stop;
}
