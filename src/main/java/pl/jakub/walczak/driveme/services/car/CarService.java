package pl.jakub.walczak.driveme.services.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.mappers.car.CarMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.repos.car.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;
    private CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public CarDTO mapModelToDTO(Car model, CarDTO dto) {
        return carMapper.mapModelToDTO(model, dto);
    }

    public Car mapDTOToModel(CarDTO dto, Car model) {
        if (dto.getId() != null) {
            Optional<Car> optionalCar = carRepository.findById(dto.getId());
            if (optionalCar.isPresent()) {
                model = optionalCar.get();
            }
        }
        model = carMapper.mapDTOToModel(dto, model);
        return carRepository.save(model);
    }

    public List<CarDTO> getAll() {
        return findAll().stream().map(car -> mapModelToDTO(car, CarDTO.builder().build())).collect(Collectors.toList());
    }
}
