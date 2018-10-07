package pl.jakub.walczak.driveme.mappers.car;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.model.car.Car;

@Component
public class CarMapper {

    public CarDTO mapModelToDTO(Car model, CarDTO dto) {
        return dto;
    }

    public Car mapDTOToModel(CarDTO dto, Car model) {
        return model;
    }
}
