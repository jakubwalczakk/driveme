package pl.jakub.walczak.driveme.mappers.car;

import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.dto.car.CarBasicDTO;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.enums.CarBrand;
import pl.jakub.walczak.driveme.enums.GasType;
import pl.jakub.walczak.driveme.model.car.Car;

import java.util.Base64;

@Component
public class CarMapper {

    public CarDTO mapModelToDTO(Car model, CarDTO dto) {
        dto.setId(model.getId());
        dto.setBrand(model.getBrand().getValue());
        dto.setModel(model.getModel());
        dto.setLicensePlate(model.getLicensePlate());
        dto.setGasType(model.getGasType().getValue());
        dto.setActive(model.getActive());
        try {
            dto.setPhoto(Base64.getEncoder().encodeToString(model.getPhoto()));
        } catch (Exception e) {
            e.printStackTrace();
            dto.setPhoto(null);
        }
        return dto;
    }

    public CarBasicDTO mapModelToBasicDTO(Car model, CarBasicDTO dto) {
        dto.setId(model.getId());
        dto.setBrand(model.getBrand().getValue());
        dto.setModel(model.getModel());
        dto.setLicensePlate(model.getLicensePlate());
        dto.setGasType(model.getGasType().getValue());
        return dto;
    }

    public Car mapDTOToModel(CarDTO dto, Car model) {
        model.setId(dto.getId());
        try {
            model.setBrand(dto.getBrand() == null ? model.getBrand() : CarBrand.of(dto.getBrand()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setBrand(CarBrand.DEFAULT);
        }
        model.setModel(dto.getModel() == null ? model.getModel() : dto.getModel());
        model.setLicensePlate(dto.getLicensePlate() == null ? model.getLicensePlate() : dto.getLicensePlate());
        try {
            model.setGasType(dto.getGasType() == null ? model.getGasType() : GasType.of(dto.getGasType()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.setGasType(GasType.DEFAULT);
        }
        model.setActive(dto.getActive() == null ? model.getActive() : dto.getActive());
        try {
            model.setPhoto(dto.getPhoto() == null ? model.getPhoto() : Base64.getDecoder().decode(dto.getPhoto()));
        } catch (Exception e) {
            e.printStackTrace();
            model.setPhoto(null);
        }
        return model;
    }
}
