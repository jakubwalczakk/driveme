package pl.jakub.walczak.driveme.dto.car;

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
    private String carPhoto;
    private Boolean active;
}
