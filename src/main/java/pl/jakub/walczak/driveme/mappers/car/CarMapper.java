package pl.jakub.walczak.driveme.mappers.car;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.model.car.Car;

@Component
public class CarMapper {

    public CarDTO mapModelToDTO(Car model, CarDTO dto) {
        dto.setId(model.getId());
        dto.setBrand(model.getBrand().toString());
        dto.setModel(model.getModel());
        dto.setLicensePlate(model.getLicensePlate());
        dto.setGasType(model.getGasType().toString());
        dto.setActive(model.getActive());
        return dto;
    }

    public Car mapDTOToModel(CarDTO dto, Car model) {
        model.setId(dto.getId());
        try {
            model.setBrand(CarBrand.valueOf(dto.getBrand().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setBrand(CarBrand.DEFAULT);
        }
        model.setModel(dto.getModel());
        model.setLicensePlate(dto.getLicensePlate());
        try {
            model.setGasType(GasType.valueOf(dto.getGasType().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            model.setGasType(GasType.DEFAULT);
        }
        model.setActive(dto.getActive());
        return model;
    }
}
