package pl.jakub.walczak.driveme.services.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.dto.car.CarDTO;
import pl.jakub.walczak.driveme.mappers.car.CarMapper;
import pl.jakub.walczak.driveme.model.car.Car;
import pl.jakub.walczak.driveme.repos.car.CarRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
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

    // -- methods for controller --
    public Car addCar(CarDTO carDTO) {
        Car car = mapDTOToModel(carDTO, Car.builder().build());
        car.setActive(true);
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Optional<Car> carToDelete = carRepository.findById(id);
        if (carToDelete.isPresent()) {
            Car car = carToDelete.get();
            car.setActive(false);
            carRepository.save(car);
        } else {
            throw new NoSuchElementException();
        }
    }

    public CarDTO getCar(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            return mapModelToDTO(optionalCar.get(), CarDTO.builder().build());
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<CarDTO> getActiveCars() {
        return findActiveCars().stream().map(car -> mapModelToDTO(car, CarDTO.builder().build()))
                .collect(Collectors.toList());
    }

    public List<CarDTO> getAll() {
        return findAll().stream().map(car -> mapModelToDTO(car, CarDTO.builder().build()))
                .collect(Collectors.toList());
    }

    // -- dao methods --
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    private Set<Car> findActiveCars() {
        return carRepository.findAllByActive(true);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    // -- mapper methods --
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
}
