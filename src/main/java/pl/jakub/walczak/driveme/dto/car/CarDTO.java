package pl.jakub.walczak.driveme.dto.car;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private Long id;
    private String brand;
    private String model;
    private String licensePlate;
    private String gasType;
//    @JsonIgnore
    private String photo;
    private Boolean active;
}
