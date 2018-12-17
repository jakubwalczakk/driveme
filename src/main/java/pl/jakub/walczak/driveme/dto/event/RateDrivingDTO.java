package pl.jakub.walczak.driveme.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RateDrivingDTO {
    private Long drivingId;
    private String rating;
    private String comment;
}
